package io.uml.contracts.controller;

import io.uml.contracts.storage.impl.ContractStorage;
import io.uml.contracts.util.WebControllerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static io.uml.contracts.util.TemplateMapper.CONTRACTS;
import static io.uml.contracts.util.TemplateMapper.CONTRACT_ADD;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 13.06.2019
 */
@RestController(WebControllerMapper.CONTRACTS)
public class ContractWebController {

    @Autowired
    private ContractStorage contractStorage;

    @GetMapping
    public ModelAndView getContracts() {
        final ModelAndView view = new ModelAndView(CONTRACTS);
        view.addObject("contracts", contractStorage.findAll());
        return view;
    }

    @GetMapping("/create")
    public ModelAndView getContractAdd() {
        return new ModelAndView(CONTRACT_ADD);
    }
}
