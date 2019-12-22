package io.uml.contracts.controller;

import io.uml.contracts.ContractRunner;
import io.uml.contracts.config.WebMapper;
import io.uml.contracts.model.dao.CleaningLog;
import io.uml.contracts.model.dao.Contract;
import io.uml.contracts.model.dao.Flight;
import io.uml.contracts.storage.impl.ContractStorage;
import io.uml.contracts.storage.impl.FlightStorage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class FlightControllerTests extends ContractRunner {

    @Autowired
    private FlightStorage flightStorage;
    @Autowired
    private ContractStorage contractStorage;

    @Autowired
    private FlightWebController flightWebController;

    @Test
    public void deleteValid() {
        final Contract contract = new Contract();
        contract.setId(UUID.randomUUID().toString());
        contractStorage.save(contract);

        final Flight flight = new Flight();
        flight.setId(UUID.randomUUID().toString());
        contract.setFlight(flight);
        flight.setContract(contract);
        flightStorage.save(flight);

        final boolean exist = flightStorage.exist(flight.getId());
        assertTrue(exist);

        flightWebController.delete(flight.getId());

        final boolean existAfter = flightStorage.exist(flight.getId());
        assertFalse(existAfter);
    }

    @Test
    public void addValid() {
        final Contract contract = new Contract();
        contract.setId(UUID.randomUUID().toString());
        contractStorage.save(contract);

        final String details = "Test Details";
        final String planets = "Mars\nMoon";
        flightWebController.add(details, planets, contract.getId());

        final List<Flight> all = flightStorage.findAll();
        assertFalse(all.isEmpty());

        final Optional<Flight> found = all.stream()
                .filter(f -> f.getRouteDetails().equals(details))
                .findFirst();

        assertTrue(found.isPresent());
        assertEquals(2, found.get().getPlanetsList().size());
    }
}
