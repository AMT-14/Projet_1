package ch.heigvd.amt.infrastructure.persistance.memory;

import ch.heigvd.amt.domain.user.IUserRepository;
import ch.heigvd.amt.domain.user.User;
import ch.heigvd.amt.domain.user.UserId;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryUserRepository extends InMemoryRepository<User, UserId> implements IUserRepository {
    @Override
    public void save(User entity) {
        // unique username constraint
        synchronized (entity.getUsername()){
            if (!findByUsername(entity.getUsername()).isEmpty()){
                throw new IntegrityConstraintViolationException("Cannot save or update User. Integrity constraint violation: username already exists");
            }
            super.save(entity);
        }

    }

    @Override
    public Optional<User> findByUsername(String username) {
        List<User> matchEntities = findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .collect(Collectors.toList());

        if (matchEntities.size() < 1){return Optional.empty();}

        if (matchEntities.size() > 1){
            throw new DataCorruptionException("Your data stored is corrupted");
        }
        return Optional.of(matchEntities.get(0).deepClone());
    }
}
