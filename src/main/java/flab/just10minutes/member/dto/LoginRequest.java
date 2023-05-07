package flab.just10minutes.member.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class LoginRequest {

    @NotNull
    private final String id;
    @NotNull
    private final String password;
}
