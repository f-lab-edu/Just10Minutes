package flab.just10minutes.member.service;

import flab.just10minutes.member.domain.Member;
import flab.just10minutes.member.dto.AddMemberRequest;

public interface MemberService {
    void saveMember(AddMemberRequest addMember);

    Member findMemberById(String id);

    void checkDuplicateId(String id);

    void existMemberValidate(String id, String password);
}
