//package ch.heigvd.amt.infrastructure.persistence.memory;
//
//import ch.heigvd.amt.domain.IEntity;
//import ch.heigvd.amt.domain.IRepository;
//import ch.heigvd.amt.domain.Id;
//
//
//import java.util.Collection;
//import java.util.Map;
//import java.util.Optional;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.stream.Collectors;
//
//public abstract class InMemoryRepository<ENTITY extends IEntity<ENTITY, ID>, ID extends Id> implements IRepository<ENTITY, ID> {
//
//    private Map<ID, ENTITY> store = new ConcurrentHashMap<>();
//
//    public void save(ENTITY entity){
//        entity.getId();
//        store.put(entity.getId(), entity);
//    }
//
//    public void remove(ID id) {store.remove(id);}
//    public Optional<ENTITY> findById(ID id){
//        ENTITY existingEntity = store.get(id);
//        if(existingEntity == null){
//            return Optional.empty();
//        }
//        ENTITY clonedEntity = existingEntity.deepClone();
//        return Optional.of(clonedEntity);
//    }
//
//    public Collection<ENTITY> findAll(){
//        return store.values().stream()
//                .map(entity->entity.deepClone())
//                .collect(Collectors.toList());
//    }
//}
