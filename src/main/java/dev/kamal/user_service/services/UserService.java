package dev.kamal.user_service.services;

import dev.kamal.user_service.models.Token;
import dev.kamal.user_service.models.User;
import dev.kamal.user_service.repositories.TokenRepository;
import dev.kamal.user_service.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       TokenRepository tokenRepository){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    public Token login(String email, String password){
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isEmpty()){
            // call SignUp method
            throw new RuntimeException("User with email: " + email +" not found in DB");
        }

        User user = optionalUser.get();
        if(bCryptPasswordEncoder.matches(password, user.getHashedPassword())){
            // generate the token
            Token token = createToken(user);
            Token savedToken = tokenRepository.save(token);

            return savedToken;
        }

        return null;
    }

    public User signUp(String name,
                       String email,
                       String password){
        User user = new User();
        user.setName(name);
        user.setEmail(email);

        // First encrypt the password using Bcrypt algo before storing into DB.
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }

    public void logout(String tokenValue){
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedAndExpiryAtGreaterThan(
                tokenValue,
                false,
                new Date()
        );

        if(optionalToken.isEmpty()){
            // throw TokenInvaledException
            return;
        }

        Token token = optionalToken.get();
        token.setDeleted(true);
        tokenRepository.save(token);
    }

    public User validateToken(String tokenValue){
        // first find out that the token with the value is present in the DB or not.
        // expiry time of the token > current time and deleted should be false.

        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedAndExpiryAtGreaterThan(
                tokenValue,
                false,
                new Date() // current Time
        );

        if(optionalToken.isEmpty()){
            return null;
        }

        return optionalToken.get().getUser();
    }

    private Token createToken(User user){
        Token token = new Token();

        token.setUser(user);
        token.setValue(RandomStringUtils.randomAlphanumeric(128));

        // Expiry time of the token is let's say 30 days from now.
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysAfterCurrentTime = today.plusDays(30);

        Date exiryAt = Date.from(thirtyDaysAfterCurrentTime.atStartOfDay(ZoneId.systemDefault()).toInstant());
        token.setExpiryAt(exiryAt);

        return token;
    }
}
