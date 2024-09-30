package dev.kamal.user_service.dtos;

import dev.kamal.user_service.models.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    private String name;
    private String email;
    private String password;
}
