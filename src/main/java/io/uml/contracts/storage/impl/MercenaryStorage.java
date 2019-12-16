package io.uml.contracts.storage.impl;

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
        findByEmail(LORD_UID).orElseGet(() -> {
            final Mercenary mercenary = new Mercenary();
            mercenary.setName("Star");
            mercenary.setSurname("Lord");
            mercenary.setEmail(LORD_UID);
            mercenary.setPassword(LORD_PASSWORD);
            mercenary.setId(UUID.randomUUID().toString());
            return save(mercenary).orElse(null);
        });

        findByEmail(DRAKS_UID).orElseGet(() -> {
            final Mercenary mercenary = new Mercenary();
            mercenary.setName("Draks");
            mercenary.setSurname("The Destroyer");
            mercenary.setEmail(DRAKS_UID);
            mercenary.setPassword(DRAKS_PASSWORD);
            mercenary.setId(UUID.randomUUID().toString());
            return save(mercenary).orElse(null);
        });

        findByEmail(GAMORA_UID).orElseGet(() -> {
            final Mercenary mercenary = new Mercenary();
            mercenary.setName("Gamora");
            mercenary.setSurname("Tanos Daughter");
            mercenary.setEmail(GAMORA_UID);
            mercenary.setPassword(GAMORA_PASSWORD);
            mercenary.setId(UUID.randomUUID().toString());
            return save(mercenary).orElse(null);
        });

        findByEmail(ROCKET_UID).orElseGet(() -> {
            final Mercenary mercenary = new Mercenary();
            mercenary.setName("Rocket");
            mercenary.setSurname("Raccoon");
            mercenary.setEmail(ROCKET_UID);
            mercenary.setPassword(ROCKET_PASSWORD);
            mercenary.setId(UUID.randomUUID().toString());
            return save(mercenary).orElse(null);
        });
    }

    public Optional<Mercenary> findByEmail(String email) {
        return mercenaryRepository.findByEmail(email);
    }
}
