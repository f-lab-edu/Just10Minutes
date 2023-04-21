package flab.just10minutes.member.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfoResponse {

    private String id;
    private String name;
    private String address;
    private Long balance;

    @Builder
    public MemberInfoResponse(String id, String name, String address, Long balance) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.balance = balance;
    }


}
