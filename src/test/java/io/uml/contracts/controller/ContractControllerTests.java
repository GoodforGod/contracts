package io.uml.contracts.controller;

import io.uml.contracts.ContractRunner;
import io.uml.contracts.model.dao.Contract;
import io.uml.contracts.model.dao.Flight;
import io.uml.contracts.storage.impl.ContractStorage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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
public class ContractControllerTests extends ContractRunner {

    @Autowired
    private ContractStorage contractStorage;

    @Autowired
    private ContractWebController contractWebController;

    @Test
    public void deleteValid() {
        final Contract contract = new Contract();
        contract.setId(UUID.randomUUID().toString());
        contractStorage.save(contract);

        final boolean exist = contractStorage.exist(contract.getId());
        assertTrue(exist);

        contractWebController.deleteContract(contract.getId());

        final boolean existAfter = contractStorage.exist(contract.getId());
        assertFalse(existAfter);
    }

    @Test
    public void commentContract() {
        final Contract contract = new Contract();
        contract.setId(UUID.randomUUID().toString());
        contractStorage.save(contract);

        final boolean exist = contractStorage.exist(contract.getId());
        assertTrue(exist);

        final String comment = "Test Comment";
        contractWebController.commentContract(contract.getId(), comment);

        final Optional<Contract> found = contractStorage.find(contract.getId());
        assertTrue(found.isPresent());
        assertEquals(1, found.get().getComments().size());
    }

    @Test
    public void createContract() {
        contractWebController.createContract(Contract.ContractType.CAPTURE,
                "Titan",
                "Titan",
                "Titan",
                "Titan",
                "Titan");

        final List<Contract> all = contractStorage.findAll();
        assertFalse(all.isEmpty());

        final Optional<Contract> found = all.stream()
                .filter(c -> c.getDescription().equals("Titan"))
                .findFirst();
        assertTrue(found.isPresent());
    }
}
