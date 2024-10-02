package dev.kamal.user_service.controllers;

import dev.kamal.user_service.dtos.*;
import dev.kamal.user_service.dtos.ResponseStatus;
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
    public void logout(@RequestBody LogoutRequestDto requestDto){
        userService.logout(requestDto.getToken());
    }

    @GetMapping("/validate")
    public UserDto validateToken(String token){
        User user = userService.validateToken(token);

        // convert user into userDto
        return UserDto.from(user);
    }

}
