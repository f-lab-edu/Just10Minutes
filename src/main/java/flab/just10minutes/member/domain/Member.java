package flab.just10minutes.member.domain;

import lombok.*;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Member {

    @Getter private String id;
    private String password;
    @Getter private String name;
    @Getter private String address;
    @Getter private Long balance;

    public Optional<String> getOptionalId() {
       return Optional.ofNullable(this.id);
    }

}
