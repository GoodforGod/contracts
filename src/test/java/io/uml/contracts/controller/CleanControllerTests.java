package io.uml.contracts.controller;

import io.uml.contracts.ContractRunner;
import io.uml.contracts.config.WebMapper;
import io.uml.contracts.model.dao.CleaningLog;
import io.uml.contracts.storage.impl.CleaningLogStorage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 22.12.2019
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class CleanControllerTests extends ContractRunner {

    @Autowired
    private CleaningLogStorage cleaningLogStorage;

    @Autowired
    private CleanWebController cleanWebController;

    @Test
    public void deleteValid() {
        final CleaningLog log = new CleaningLog();
        log.setId(UUID.randomUUID().toString());
        log.setCleanDate(Timestamp.valueOf(LocalDateTime.now()));
        cleaningLogStorage.save(log);

        final boolean exist = cleaningLogStorage.exist(log.getId());
        assertTrue(exist);

        cleanWebController.delete(log.getId());

        final boolean existAfter = cleaningLogStorage.exist(log.getId());
        assertFalse(existAfter);
    }

    @Test
    public void addValid() {
        final String descr = "Test Description";
        cleanWebController.add(LocalDate.now().toString(), descr);

        final List<CleaningLog> all = cleaningLogStorage.findAll();
        final Optional<CleaningLog> found = all.stream().filter(l -> l.getDescription().equals(descr)).findFirst();
        assertTrue(found.isPresent());
    }
}
