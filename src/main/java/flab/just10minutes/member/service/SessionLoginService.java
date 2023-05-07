package flab.just10minutes.member.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService{


    private final HttpSession httpSession;

    @Override
    public void logIn(Long openId) {
        httpSession.setAttribute("loginMember", openId);
    }

    @Override
    public void logOut() {
//        boolean isLogin = isLogin();
//        if (!isLogin) {
//            throw new IllegalStateException("로그인 되어있지 않습니다. 로그인 해주세요.");
//        }
        httpSession.invalidate();
    }

    public boolean isLogin() {
        return Optional
                .ofNullable(httpSession.getAttribute("loginMember"))
                .isPresent();
    }

    @Override
    public void loginValidate() {
        Boolean isLogin = isLogin();
        if (!isLogin) {
            throw new IllegalStateException("로그인 되어있지 않습니다.");
        }
    }


}

