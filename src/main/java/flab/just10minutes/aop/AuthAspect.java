package flab.just10minutes.aop;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Aspect
@RequiredArgsConstructor
public class AuthAspect {

    private final HttpSession httpSession;

    @Before("@annotation(flab.just10minutes.aop.MemberLoginCheck)")
    public void execute(JoinPoint joinPoint) {
        Boolean isLogin = isLogin();
        if (!isLogin) {
            throw new IllegalStateException("로그인 되어있지 않습니다.");
        }
    }

    private boolean isLogin() {
        return Optional
                .ofNullable(httpSession.getAttribute("loginMember"))
                .isPresent();
    }
}
