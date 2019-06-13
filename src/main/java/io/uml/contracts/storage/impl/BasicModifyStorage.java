package io.uml.contracts.storage.impl;

import io.uml.contracts.model.dao.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Basic modify storage
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
public abstract class BasicModifyStorage<T extends BaseModel<ID>, ID extends Serializable> extends BasicStorage<T, ID> {

    public BasicModifyStorage(JpaRepository<T, ID> repository) {
        super(repository);
    }

    @Override
    public Optional<T> save(T t) {
        if (!isValid(t))
            return Optional.empty();

        t.modify();
        return super.save(t);
    }

    @Override
    public List<T> save(Collection<T> t) {
        return (!isValid(t))
                ? Collections.emptyList()
                : super.save(t.stream().peek(BaseModel::modify).collect(Collectors.toList()));
    }
}
