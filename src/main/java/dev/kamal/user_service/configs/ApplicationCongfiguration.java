package dev.kamal.user_service.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationCongfiguration {

    @Bean
    public BCryptPasswordEncoder getPasswordEcnoder(){
        return new BCryptPasswordEncoder();
    }
}
