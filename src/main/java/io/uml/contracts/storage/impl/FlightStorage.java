package io.uml.contracts.storage.impl;

import io.uml.contracts.model.dao.Flight;
import io.uml.contracts.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Component
public class FlightStorage extends BasicModifyStorage<Flight, String> {

    @Autowired
    public FlightStorage(FlightRepository repository) {
        super(repository);
    }
}
