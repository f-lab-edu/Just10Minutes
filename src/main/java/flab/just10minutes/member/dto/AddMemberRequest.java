package flab.just10minutes.member.dto;

import flab.just10minutes.member.domain.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddMemberRequest {


    private final String id;
    private final String password;
    private final String name;
    private final String address;

    public static Member to(AddMemberRequest addMemberRequest) {
        return Member.builder()
                .id(addMemberRequest.id)
                .password(addMemberRequest.password)
                .name(addMemberRequest.name)
                .address(addMemberRequest.address)
                .build();
    }
}
