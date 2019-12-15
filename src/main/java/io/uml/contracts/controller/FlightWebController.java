package io.uml.contracts.controller;

import io.uml.contracts.config.WebMapper;
import io.uml.contracts.controller.error.ResourceNotFoundException;
import io.uml.contracts.model.dao.Contract;
import io.uml.contracts.model.dao.Flight;
import io.uml.contracts.storage.impl.ClientStorage;
import io.uml.contracts.storage.impl.ContractStorage;
import io.uml.contracts.storage.impl.FlightStorage;
import io.uml.contracts.storage.impl.MercenaryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.uml.contracts.config.TemplateMapper.PAGE_FLIGHT_ADD;
import static io.uml.contracts.config.TemplateMapper.PAGE_FLIGHT_VIEW;
import static io.uml.contracts.config.WebMapper.redirect;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 14.12.2019
 */
@Controller
public class FlightWebController extends BaseWebController {

    private final FlightStorage flightStorage;
    private final ContractStorage contractStorage;

    @Autowired
    public FlightWebController(ClientStorage clientStorage,
                               MercenaryStorage mercenaryStorage,
                               FlightStorage flightStorage,
                               ContractStorage contractStorage) {
        super(clientStorage, mercenaryStorage);
        this.flightStorage = flightStorage;
        this.contractStorage = contractStorage;
    }

    @GetMapping(WebMapper.FLIGHT_VIEW + "/{id}")
    public ModelAndView view(@PathVariable("id") String id) {
        final ModelAndView view = new ModelAndView(PAGE_FLIGHT_VIEW);
        final Flight flight = flightStorage.find(id).orElseThrow(ResourceNotFoundException::new);
        view.addObject("flight", flight);
        view.addObject("role", getRoleFromContext());
        return view;
    }

    @DeleteMapping(WebMapper.FLIGHT_VIEW + "/{id}")
    public String delete(@PathVariable("id") String id) {
        final Optional<Flight> flight = flightStorage.find(id);
        final Optional<String> contractId = flight.map(f -> f.getContract().getId());
        flight.ifPresent(t -> {
            t.setContract(null);
            flightStorage.save(t);
            flightStorage.delete(t.getId());
        });

        return contractId.map(s -> redirect(WebMapper.CONTRACT_TABLE + "/" + s)).orElseGet(() -> redirect(WebMapper.CONTRACT_TABLE));
    }

    @GetMapping(WebMapper.FLIGHT_ADD + "/{id}")
    public ModelAndView addPage(@PathVariable("id") String contract) {
        final ModelAndView view = new ModelAndView(PAGE_FLIGHT_ADD);
        view.addObject("contract", contract);
        return view;
    }

    @PostMapping(WebMapper.FLIGHT_ADD)
    public String add(@RequestParam("details") String details,
                      @RequestParam("planets") String planets,
                      @RequestParam("contract") String contractId) {
        final Flight flight = new Flight();
        final String planetJoined = Arrays.stream(planets.split("\n")).sequential()
                .map(p -> p.replace("\r", "").trim())
                .collect(Collectors.joining(","));

        flight.setId(UUID.randomUUID().toString());
        flight.setRouteDetails(details);
        flight.setPlanets(planetJoined);
        final Contract contract = contractStorage.find(contractId).orElseThrow(() -> new IllegalArgumentException("Contract not found"));
        flight.setContract(contract);
        flightStorage.save(flight);

        return redirect(WebMapper.FLIGHT_VIEW + "/" + flight.getId());
    }
}
