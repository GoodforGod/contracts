package io.uml.contracts.storage.impl;

import io.uml.contracts.storage.IStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Basic storage impl
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
public abstract class BasicStorage<T, ID extends Serializable>
        extends BasicStorageUtils<T, ID>
        implements IStorage<T, ID> {

    protected final JpaRepository<T, ID> repository;
    protected final Logger logger;

    public BasicStorage(JpaRepository<T, ID> repository) {
        this.repository = repository;
        this.logger = LoggerFactory.getLogger(repository.getClass());
    }

    @Override
    public boolean exist(ID id) {
        try {
            return isValid(id) && repository.existsById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<T> find(ID id) {
        try {
            return isValid(id) ? repository.findById(id) : Optional.empty();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<T> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<T> save(T t) {
        try {
            return isValid(t) ? Optional.of(repository.save(t)) : Optional.empty();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<T> save(Collection<T> t) {
        try {
            return isValid(t) ? repository.saveAll(t) : Collections.emptyList();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public boolean delete(ID id) {
        if (!isValid(id))
            return false;

        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        try {
            repository.deleteAll();
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}
