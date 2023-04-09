package flab.just10minutes.member.dto;

import flab.just10minutes.member.domain.Member;
import lombok.Value;

@Value
public class CommonResponse {

    private String id;
    private String name;
    private String address;
    private Long balance;

    public static CommonResponse of(Member member) {
        return new CommonResponse(
                member.getId(),
                member.getName(),
                member.getAddress(),
                member.getBalance()
        );
    }

}
