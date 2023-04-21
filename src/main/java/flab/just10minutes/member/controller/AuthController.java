package flab.just10minutes.member.controller;

import flab.just10minutes.member.dto.LoginRequest;
import flab.just10minutes.member.service.LoginService;
import flab.just10minutes.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody @Valid LoginRequest loginRequest) {
        boolean isMember = memberService.isValidMember(loginRequest.getId(), loginRequest.getPassword());
        if (isMember) {
            loginService.logIn(memberService.findMemberById(loginRequest.getId()).getOpenId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/logout")
    public ResponseEntity<HttpStatus> logout() {
        loginService.logOut();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}