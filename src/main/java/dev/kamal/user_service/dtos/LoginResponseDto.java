package dev.kamal.user_service.dtos;

import dev.kamal.user_service.models.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private String token;
    private ResponseStatus responseStatus;
}
