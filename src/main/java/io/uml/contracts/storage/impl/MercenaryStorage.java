package io.uml.contracts.storage.impl;

import io.uml.contracts.model.dao.Client;
import io.uml.contracts.model.dao.Mercenary;
import io.uml.contracts.repository.MercenaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static io.uml.contracts.config.SecurityConfig.*;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Component
public class MercenaryStorage extends BasicStorage<Mercenary, String> {

    private final MercenaryRepository mercenaryRepository;

    @Autowired
    public MercenaryStorage(MercenaryRepository repository) {
        super(repository);
        this.mercenaryRepository = repository;
        findByEmail(clientUid).orElseGet(() -> {
            final Mercenary mercenary = new Mercenary();
            mercenary.setName("Start");
            mercenary.setSurname("Lord");
            mercenary.setEmail(uid);
            mercenary.setPassword(password);
            mercenary.setId(UUID.randomUUID().toString());
            return save(mercenary).orElse(null);
        });
    }

    public Optional<Mercenary> findByEmail(String email) {
        return mercenaryRepository.findByEmail(email);
    }
}
