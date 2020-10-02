package ch.heigvd.amt.domain.user;

import ch.heigvd.amt.domain.IRepository;

import java.util.Optional;

public interface IUserRepository extends IRepository<User, UserId> {

    public Optional<User> findByUsername(String username);
}
