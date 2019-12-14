package io.uml.contracts.controller;

import io.uml.contracts.config.WebMapper;
import io.uml.contracts.controller.error.ResourceNotFoundException;
import io.uml.contracts.model.dao.Contract;
import io.uml.contracts.model.dao.Flight;
import io.uml.contracts.storage.impl.ClientStorage;
import io.uml.contracts.storage.impl.FlightStorage;
import io.uml.contracts.storage.impl.MercenaryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static io.uml.contracts.config.TemplateMapper.PAGE_FLIGHT_ADD;
import static io.uml.contracts.config.TemplateMapper.PAGE_FLIGHT_VIEW;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 14.12.2019
 */
@Controller
public class FlightWebController extends BaseWebController {

    private final FlightStorage flightStorage;

    @Autowired
    public FlightWebController(ClientStorage clientStorage,
                               MercenaryStorage mercenaryStorage,
                               FlightStorage flightStorage) {
        super(clientStorage, mercenaryStorage);
        this.flightStorage = flightStorage;
    }

    @GetMapping(WebMapper.FLIGHT_VIEW + "/{id}")
    public ModelAndView view(@PathVariable("id") String id) {
        final ModelAndView view = new ModelAndView(PAGE_FLIGHT_VIEW);
        final Flight flight = flightStorage.find(id).orElseThrow(ResourceNotFoundException::new);
        view.addObject("flight", flight);
        view.addObject("role", getRoleFromContext());
        return view;
    }

    @GetMapping(WebMapper.FLIGHT_ADD)
    public ModelAndView addPage() {
        return new ModelAndView(PAGE_FLIGHT_ADD);
    }

    @PostMapping(WebMapper.FLIGHT_VIEW)
    public String add(@RequestParam("type") Contract.ContractType type,
                      @RequestParam("planet") String planet,
                      @RequestParam("title") String title,
                      @RequestParam("description") String description,
                      @RequestParam("reward") String reward,
                      @RequestParam("comment") String comment) {
        final Flight flight = new Flight();

        flightStorage.save(flight);

        return WebMapper.redirect(WebMapper.FLIGHT_VIEW + "/" + flight.getId());
    }
}
