package flab.just10minutes.member.service;

import flab.just10minutes.member.dto.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionLoginService implements LoginService{

    @Autowired
    private HttpSession httpSession;

    @Override
    public void logIn(LoginRequest loginRequest) {
        httpSession.setAttribute("loginMember", loginRequest.getId());
    }

    @Override
    public void logOut(String id) {
        httpSession.invalidate();
    }
}
