package dev.kamal.user_service.services;

import dev.kamal.user_service.models.Token;
import dev.kamal.user_service.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public Token login(String email, String password){
        return null;
    }

    public User signUp(String name, String email, String password){
        return null;
    }

    public void logout(String tokenValue){

    }

    public User validateUser(String tokenValue){
        return null;
    }
}
