package io.uml.contracts.storage;

import io.uml.contracts.model.dao.BaseModel;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
public interface IStorage<T extends BaseModel<ID>, ID extends Serializable> {

    boolean exist(ID id);

    Optional<T> find(ID id);
    List<T> findAll();

    Optional<T> save(T t);
    List<T> save(Collection<T> t);

    boolean delete(T t);
    boolean delete(ID id);
    boolean deleteAll();
}
