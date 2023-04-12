package flab.just10minutes.member.controller;

import flab.just10minutes.member.dto.AddRequest;
import flab.just10minutes.member.dto.CommonResponse;
import flab.just10minutes.member.dto.LoginRequest;
import flab.just10minutes.member.service.LoginService;
import flab.just10minutes.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
/**
 * @Controller + @ResponseBody
 * Json 형태 데이터 반환이 목적**/
@RequiredArgsConstructor
/**
 * 초기화 되지 않은 final 필드나 @NotNull이 붙은 필드의 생성자를 생성
 * 의존성 주입(DI) 방식의 하나로 사용할 수 있음
 * 하지만 의존성 주입에 세부 설정이 필요하다면 생성자 주입 + @Autowired로 사용하는 것이 좋음
 * **/
@RequestMapping("/api/members")
/**
 * 클래스에서 처리할 공통 url에 대한 정의
 * **/
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
