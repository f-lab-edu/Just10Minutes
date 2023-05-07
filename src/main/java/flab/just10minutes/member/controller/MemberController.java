package flab.just10minutes.member.controller;

import flab.just10minutes.member.domain.Member;
import flab.just10minutes.member.dto.AddMemberRequest;
import flab.just10minutes.member.dto.MemberInfoResponse;
import flab.just10minutes.member.service.MemberService;
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

    @PostMapping
    public ResponseEntity<HttpStatus> addMember(@RequestBody @Valid AddMemberRequest addRequest) {
        memberService.saveMember(addRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberInfoResponse> getMemberProfile(@PathVariable String id) {
        Member member = memberService.findMemberById(id);
        MemberInfoResponse responseMemberInfo = MemberInfoResponse.to(member);
        return new ResponseEntity<MemberInfoResponse>(responseMemberInfo, HttpStatus.OK);
    }


}
