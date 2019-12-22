package io.uml.contracts.storage;

import io.uml.contracts.ContractRunner;
import io.uml.contracts.model.dao.Mercenary;
import io.uml.contracts.storage.impl.MercenaryStorage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 22.12.2019
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class StorageTests extends ContractRunner {

    @Autowired
    private MercenaryStorage storage;

    @After
    @Before
    public void clean() {
        storage.deleteAll();
    }

    private Mercenary getMercenary() {
        final Mercenary mercenary = new Mercenary();
        mercenary.setId(UUID.randomUUID().toString());
        mercenary.setEmail(ThreadLocalRandom.current().nextInt(1, 10000) + "mer@mail.ru");
        mercenary.setPassword("pass");
        return mercenary;
    }

    @Test
    public void saveValid() {
        final Mercenary mercenary = getMercenary();
        final Optional<Mercenary> save = storage.save(mercenary);
        assertTrue(save.isPresent());

        final Optional<Mercenary> found = storage.find(mercenary.getId());
        assertTrue(found.isPresent());
    }

    @Test
    public void existValid() {
        final Mercenary mercenary = getMercenary();
        final Optional<Mercenary> save = storage.save(mercenary);
        assertTrue(save.isPresent());

        final boolean exist = storage.exist(mercenary.getId());
        assertTrue(exist);
    }

    @Test
    public void findWhenExist() {
        final Mercenary mercenary = getMercenary();
        final Optional<Mercenary> save = storage.save(mercenary);
        assertTrue(save.isPresent());

        final Optional<Mercenary> found = storage.find(mercenary.getId());
        assertTrue(found.isPresent());
    }

    @Test
    public void notFoundWhenNotExist() {
        final Optional<Mercenary> found = storage.find(UUID.randomUUID().toString());
        assertFalse(found.isPresent());
    }

    @Test
    public void findAllValid() {
        final Mercenary mercenary = getMercenary();
        final Optional<Mercenary> save = storage.save(mercenary);
        assertTrue(save.isPresent());

        final List<Mercenary> all = storage.findAll();
        assertFalse(all.isEmpty());
    }

    @Test
    public void deleteWhenExist() {
        final Mercenary mercenary = getMercenary();
        final Optional<Mercenary> save = storage.save(mercenary);
        assertTrue(save.isPresent());

        final boolean delete = storage.delete(mercenary.getId());
        assertTrue(delete);

        final Optional<Mercenary> found = storage.find(mercenary.getId());
        assertFalse(found.isPresent());
    }

    @Test
    public void deleteWhenNotExist() {
        final boolean delete = storage.delete(UUID.randomUUID().toString());
        assertFalse(delete);
    }
}
