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
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberDao memberDao;


    @Override
    public void saveMember(AddRequest addMember) {
        checkDuplicateId(addMember.getId());
        Member newMember = new Member(
                addMember.getId(),
                addMember.getPassword(),
                addMember.getName(),
                addMember.getAddress(),
                0L);
        memberDao.save(newMember);
    }

    @Override
    public Member findMemberById(String id) {
        Member member = memberDao.findById(id);
        member.getOptionalId().orElseThrow(() -> new IllegalStateException("해당 아이디가 존재하지 않습니다."));
        return member;
    }



    @Override
    public void checkDuplicateId(String id) {
        Optional.ofNullable(memberDao.findId(id))
                .ifPresent(action -> {throw new IllegalStateException("해당 아이디가 이미 존재 합니다.");});
    }
}