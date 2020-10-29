package ch.heigvd.amt.domain;

import java.util.Collection;
import java.util.Optional;

public interface IRepository<IEntity, Id> {
    public void save(IEntity entity);
    public void remove(Id id);
    public void update(IEntity id);
    public Optional<IEntity> findById(Id id);
    public Collection<IEntity> findAll();
}

