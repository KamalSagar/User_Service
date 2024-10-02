package dev.kamal.user_service.repositories;

import dev.kamal.user_service.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByValueAndDeletedAndExpiryAtGreaterThan(String tokenValue,
                                                                boolean deleted,
                                                                Date currentTime);

    /*
    Select * from tokens
    where value = tokenValue and
    deleted = false and
    expiryAt > currentTime;
     */

    Token save(Token token);
    // Update + Insert => Upsert
}
