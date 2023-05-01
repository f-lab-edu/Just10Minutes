package flab.just10minutes.member.service;

import flab.just10minutes.member.dto.LoginRequest;

public interface LoginService {
    void logIn(Long uniqueId);
    void logOut();
    void loginValidate();
}
