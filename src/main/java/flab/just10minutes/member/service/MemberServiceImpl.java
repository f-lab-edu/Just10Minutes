package flab.just10minutes.member.service;

import flab.just10minutes.member.domain.Member;
import flab.just10minutes.member.dto.AddRequest;
import flab.just10minutes.member.repository.MemberDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberDao memberDao;


    @Override
    public void saveMember(AddRequest addRequest) {
        Member newMember = AddRequest.toMemberDomain(addRequest);
        checkDuplicateId(newMember.getId());
        int insertCount = memberDao.save(newMember);
        if (insertCount != 1) {
            throw new IllegalStateException("회원가입 실패");
        }
    }

    @Override
    public Member findMemberById(String id) {
        Member member = memberDao.findById(id);
        Optional.ofNullable(member.getId()).orElseThrow(() -> new IllegalStateException("해당 아이디가 존재하지 않습니다."));
        return member;
    }

    @Override
    public void checkDuplicateId(String id) {
        Optional.ofNullable(memberDao.findId(id))
                .ifPresent(action -> {throw new IllegalStateException("해당 아이디가 이미 존재 합니다.");});
    }

    @Override
    public boolean isValidMember(String id, String password) {
        Member member = memberDao.findById(id);
        if (!member.getPassword().equals(password)) {
            throw new IllegalStateException("아이디 혹은 비밀번호를 다시 확인해주세요.");
        }
        return true;
    }
}