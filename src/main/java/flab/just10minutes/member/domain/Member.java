package flab.just10minutes.member.domain;

import lombok.*;
import org.springframework.util.Assert;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    private Long uniqueId;
    private String id;
    private String password;
    private String name;
    private String address;
    private Long balance;

    @Builder
    public Member(String id, String password, String name, String address) {
        Assert.notNull(id, "id must not be null");
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
        this.balance = 0L;
    }

    @Builder(builderClassName = "AuthenticateBuilder", builderMethodName = "AuthenticateBuilder")
    public Member(String id, String password) {
        Assert.notNull(id, "id must not be null");
        Assert.notNull(password, "password must not be null");
        this.id = id;
        this.password = password;
    }

    @Builder(builderClassName = "ChangeBalanceBuilder", builderMethodName = "ChangeBalanceBuilder")
    public Member(Long uniqueId, Long balance) {
        this.uniqueId = uniqueId;
        this.balance = balance;
    }

    public Member minusBalance(Long value) {
        Long balance = this.balance - value;
        return Member.ChangeBalanceBuilder()
                .uniqueId(this.uniqueId)
                .balance(balance)
                .build();
    }


}
