package dev.kamal.user_service.controllers;

import dev.kamal.user_service.dtos.LoginRequestDto;
import dev.kamal.user_service.dtos.LoginResponseDto;
import dev.kamal.user_service.dtos.ResponseStatus;
import dev.kamal.user_service.dtos.UserDto;
import dev.kamal.user_service.dtos.SignUpRequestDto;
import dev.kamal.user_service.models.Token;
import dev.kamal.user_service.models.User;
import dev.kamal.user_service.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    // login , signup, validateToken, logout
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponseDto login (@RequestBody LoginRequestDto requestDto){
        LoginResponseDto responseDto = new LoginResponseDto();
        try{
            Token token = userService.login(requestDto.getEmail(),
                    requestDto.getPassword());

            responseDto.setToken(token.getValue());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    @PostMapping("/signup")
    public UserDto signUp(@RequestBody SignUpRequestDto requestDto){
        User user = userService.signUp(requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword());

        UserDto userDto = UserDto.from(user);

        return userDto;
    }

    @PatchMapping("/logout")
    public void logout(@RequestBody LoginRequestDto loginRequestDto){

    }

    @GetMapping("/validate")
    public UserDto validateToken(String token){
        return null;
    }

}
