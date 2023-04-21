package flab.just10minutes.member.service;

import flab.just10minutes.member.dto.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionLoginService implements LoginService{

    @Autowired
    private HttpSession httpSession;

    @Override
    public void logIn(Long openId) {
        loginValidate();
        httpSession.setAttribute("loginMember", openId);
    }

    @Override
    public void logOut() {
        boolean isLogin = isLogin();
        if (!isLogin) {
            throw new IllegalStateException("로그인 되어있지 않습니다. 로그인 해주세요.");
        }
        httpSession.invalidate();
    }


    private boolean isLogin() {
        return Optional
                .ofNullable(httpSession.getAttribute("loginMember"))
                .isPresent();
    }

    @Override
    public void loginValidate() {
        Boolean isLogin = isLogin();
        if (!isLogin) {
            throw new IllegalStateException("이미 로그인되어 있습니다.");
        }
    }


}

