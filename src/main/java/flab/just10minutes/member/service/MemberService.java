package flab.just10minutes.member.service;

import flab.just10minutes.member.domain.Member;
import flab.just10minutes.member.dto.AddRequest;

public interface MemberService {
    void saveMember(AddRequest addMember);

    Member findMemberById(String id);

    void checkDuplicateId(String id);

    void isValidMember(String id, String password);
}
