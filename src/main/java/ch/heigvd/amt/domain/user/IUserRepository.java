package ch.heigvd.amt.domain.user;

import java.util.Optional;

public interface IUserRepository {
    Optional<Object> findByUsername(String username);
}
