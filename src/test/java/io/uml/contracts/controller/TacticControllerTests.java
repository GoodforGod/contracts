package io.uml.contracts.controller;

import io.uml.contracts.ContractRunner;
import io.uml.contracts.config.WebMapper;
import io.uml.contracts.model.dao.*;
import io.uml.contracts.storage.impl.ContractStorage;
import io.uml.contracts.storage.impl.MercenaryStorage;
import io.uml.contracts.storage.impl.TacticStorage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 22.12.2019
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class TacticControllerTests extends ContractRunner {

    @Autowired
    private TacticStorage tacticStorage;
    @Autowired
    private ContractStorage contractStorage;
    @Autowired
    private MercenaryStorage mercenaryStorage;

    @Autowired
    private TacticWebController tacticWebController;

    @Test
    public void deleteValid() {
        final Contract contract = new Contract();
        contract.setId(UUID.randomUUID().toString());
        contractStorage.save(contract);

        final Tactic tactic = new Tactic();
        tactic.setId(UUID.randomUUID().toString());
        tactic.setContract(contract);
        tacticStorage.save(tactic);

        final boolean exist = tacticStorage.exist(tactic.getId());
        assertTrue(exist);

        tacticWebController.delete(tactic.getId());

        final boolean existAfter = tacticStorage.exist(tactic.getId());
        assertFalse(existAfter);
    }

    @Test
    public void addValid() {
        final Contract contract = new Contract();
        contract.setId(UUID.randomUUID().toString());
        contractStorage.save(contract);

        final Map<String, String> roles = mercenaryStorage.findAll().stream()
                .collect(Collectors.toMap(Mercenary::getName, m -> TacticRole.TacticRoleTypes.ATTACK.name()));

        final String tactic = "Super";
        tacticWebController.add(contract.getId(), tactic, roles);

        final Optional<Contract> found = contractStorage.find(contract.getId());
        assertTrue(found.isPresent());
        assertEquals(tactic, found.get().getTactic().getName());
    }

    @Test
    public void commentValid() {
        final Contract contract = new Contract();
        contract.setId(UUID.randomUUID().toString());
        final Tactic tactic = new Tactic();
        tactic.setId(UUID.randomUUID().toString());
        tactic.setName("Super");
        contract.setTactic(tactic);
        tactic.setContract(contract);
        contractStorage.save(contract);

        assertTrue(tacticStorage.exist(tactic.getId()));

        final String comment = "Test Comment";
        tacticWebController.commentTactic(tactic.getId(), comment);

        final Optional<Tactic> found = tacticStorage.find(tactic.getId());
        assertTrue(found.isPresent());

        assertEquals(1, found.get().getComments().size());
    }
}
