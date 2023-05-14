package flab.just10minutes.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class NamedLockAspect {
    private static final String GET_LOCK = "SELECT GET_LOCK(?, ?)";
    private static final String RELEASE_LOCK = "SELECT RELEASE_LOCK(?)";
    private static final String EXCEPTION_MESSAGE = "LOCK을 수행하는 중 오류가 발생했습니다.";

    private final DataSource dataSource;

    @Around("@annotation(flab.just10minutes.aop.NamedLock)")
    public Object executeWithLock(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("here");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        NamedLock namedLock = signature.getMethod().getAnnotation(NamedLock.class);
        try (Connection con = dataSource.getConnection()){
            try {
                log.info("start get lock");
                getLock(con, namedLock.lockName(), namedLock.timeoutSecond());
                return joinPoint.proceed();
            } finally {
                log.info("start release lock");
                releaseLock(con, namedLock.lockName());
                log.info("success release lock");
            }
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void getLock(Connection con, String lockName, int timeoutSecond) throws SQLException {
        try {
            PreparedStatement pstmt = con.prepareStatement(GET_LOCK);
            pstmt.setString(1, getLockName(lockName));
            pstmt.setInt(2, timeoutSecond);
            checkRs(lockName, pstmt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void releaseLock(Connection con, String lockName) throws SQLException {
        try {
            PreparedStatement pstmt = con.prepareStatement(RELEASE_LOCK);
            pstmt.setString(1, getLockName(lockName));
            checkRs(lockName, pstmt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getLockName(String lockName) throws Exception {
        try {
            return String.valueOf(LockName.valueOf(lockName));
        } catch (IllegalArgumentException e) {
            throw new Exception("compile error : lockName이 데이터베이스에 존재하지 않습니다.");
        }
    }

    private void checkRs(String lockName, PreparedStatement pstmt) throws SQLException {
        try {
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                log.info("NAMED LOCK 쿼리 결과가 없습니다");
            }
            int result = rs.getInt(1);
            if (result != 1) {
                log.info("락을 획득하지 못했습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw new RuntimeException(EXCEPTION_MESSAGE);
        }
    }


    private enum LockName {
        MEMBER,
        PRODUCT
    }
}
