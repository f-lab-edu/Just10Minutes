package flab.just10minutes.member.controller;

import flab.just10minutes.member.dto.AddRequest;
import flab.just10minutes.member.dto.CommonResponse;
import flab.just10minutes.member.dto.LoginRequest;
import flab.just10minutes.member.service.LoginService;
import flab.just10minutes.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<HttpStatus> addMember(@RequestBody @Valid AddRequest addMember) {
        memberService.saveMember(addMember);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getMemberProfile(@PathVariable String id) {
        CommonResponse response = CommonResponse.of(memberService.findMemberById(id));
        return new ResponseEntity<CommonResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            throw new IllegalStateException("이미 로그인되어 있습니다.");
        }
        memberService.isValidMember(loginRequest.getId(), loginRequest.getPassword());
        loginService.logIn(loginRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<HttpStatus> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        loginService.logOut((String)session.getAttribute("loginMember"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/session")
    public ResponseEntity<String> getSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String memberId = (String)session.getAttribute("loginMember");
        return new ResponseEntity<>(memberId, HttpStatus.OK);
    }

}
