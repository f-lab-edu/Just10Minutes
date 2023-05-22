package flab.just10minutes.aop;

import flab.just10minutes.lock.DistributedLock;
import flab.just10minutes.product.dto.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class DistributedLockAop {

    private final RedissonClient redissonClient;

    @Around("@annotation(flab.just10minutes.lock.DistributedLock) && args(purchaseRequest, ..)")
    public Object lock(final ProceedingJoinPoint joinPoint, PurchaseRequest purchaseRequest) throws Throwable {
        log.info("here");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

        String key = createKey(distributedLock.key(), purchaseRequest.getProductId());
        log.info(key);

        RLock lock = redissonClient.getLock(key);

        try {
            boolean isPossible = lock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit());
            log.info(String.valueOf(isPossible));
            if (!isPossible) {
                return false;
            }
            return joinPoint.proceed();
        } catch(RuntimeException e) {
            throw new IllegalStateException("오류 발생", e);
        }catch(Exception e) {
            throw new InterruptedException();
        } finally {
 //           if (lock != null && lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
        }
    }

    private String createKey(String key, Long lockId) {
        StringBuffer resultKey = new StringBuffer(key);
        return resultKey.append(lockId.toString()).toString();
    }
}
