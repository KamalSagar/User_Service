package dev.kamal.user_service.controllers;

import dev.kamal.user_service.dtos.LoginRequestDto;
import dev.kamal.user_service.dtos.LoginResponseDto;
import dev.kamal.user_service.dtos.UserDto;
import dev.kamal.user_service.dtos.SignUpRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    // login , signup, validateToken, logout

    @PostMapping("/login")
    public LoginResponseDto login (@RequestBody LoginRequestDto loginRequestDto){
        return null;
    }

    @PostMapping("/signup")
    public UserDto signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        return null;
    }

    @PatchMapping("/logout")
    public void logout(@RequestBody LoginRequestDto loginRequestDto){

    }

    @GetMapping("/validate")
    public UserDto validateToken(String token){
        return null;
    }

}
