package flab.just10minutes.member.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
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
