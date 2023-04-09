package flab.just10minutes.member.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
/**
 * 불변 객체 생성
 * @Getter : get* 메소드
 * @AllArgsConstructor : 모든 필드를 쓴 생성자
 * @ToString : .toString();
 * @EqualsAndHashCode
 * @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
 * **/
public class AddRequest {

    @NotNull
    private final String id;
    @NotNull
    private final String password;
    @NotNull
    private final String name;
    private final String address;
    private final Long balance;
}
