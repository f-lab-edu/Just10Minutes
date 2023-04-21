package flab.just10minutes.member.dto;

import flab.just10minutes.member.domain.Member;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddRequest {

    @NotNull
    private final String id;
    @NotNull
    private final String password;
    @NotNull
    private final String name;
    private final String address;

    public static Member toMemberDomain(AddRequest addRequest) {
        return Member.builder()
                .id(addRequest.id)
                .password(addRequest.password)
                .name(addRequest.name)
                .address(addRequest.address)
                .build();
    }
}
