package io.uml.contracts.controller;

import io.uml.contracts.controller.error.NotAuthorizedException;
import io.uml.contracts.controller.error.ResourceNotFoundException;
import io.uml.contracts.model.dao.Client;
import io.uml.contracts.model.dao.Contract;
import io.uml.contracts.storage.impl.ClientStorage;
import io.uml.contracts.storage.impl.ContractStorage;
import io.uml.contracts.util.WebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

import static io.uml.contracts.util.TemplateMapper.*;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 13.06.2019
 */
@Controller
public class ContractWebController {

    @Autowired
    private ContractStorage contractStorage;

    @Autowired
    private ClientStorage clientStorage;

    @GetMapping("/")
    public String home() {
        return WebMapper.redirect(WebMapper.CONTRACTS);
    }

    @GetMapping(WebMapper.CONTRACTS + "/{id}")
    public ModelAndView viewContract(@PathVariable("id") String id) {
        final ModelAndView view = new ModelAndView(CONTRACT_CLIENT);
        final Client client = getClientFromContext();

        Contract contract = contractStorage.find(id).orElseThrow(ResourceNotFoundException::new);
        view.addObject("contract", contract);
        view.addObject("comments", contract.getComments());
        view.addObject("role", getRoleFromContext());
        return view;
    }

    @DeleteMapping(WebMapper.CONTRACTS + "/{id}")
    public String deleteContract(@PathVariable("id") String id) {
        final Client client = getClientFromContext();
        Contract contract = contractStorage.find(id).orElseThrow(ResourceNotFoundException::new);
        if(!contract.getClient().getId().equals(client.getId())) {
            throw new NotAuthorizedException();
        }

        contractStorage.delete(contract.getId());

        return WebMapper.redirect(WebMapper.CONTRACTS);
    }

    @GetMapping(WebMapper.CONTRACTS + "/approve/{id}")
    public String approveContract(@PathVariable("id") String id) {
        final Client client = getClientFromContext();
        String role = getRoleFromContext();
        if(!"ADMIN".equals(role)) {
            throw new NotAuthorizedException();
        }

        Contract contract = contractStorage.find(id).orElseThrow(ResourceNotFoundException::new);
        contract.approve();
        contractStorage.save(contract);

        return WebMapper.redirect(WebMapper.CONTRACTS);
    }

    @GetMapping(WebMapper.CONTRACTS)
    public ModelAndView getContracts() {
        final ModelAndView view = new ModelAndView(CONTRACTS);
        final Client client = getClientFromContext();

        List<Contract> contracts = contractStorage.findByClient(client.getId());
        view.addObject("contracts", contracts);
        view.addObject("role", getRoleFromContext());
        return view;
    }

    @GetMapping(WebMapper.CONTRACT_ADD)
    public ModelAndView getContractAdd() {
        return new ModelAndView(CONTRACT_ADD);
    }

    @PostMapping(WebMapper.CONTRACT_ADD)
    public String createContract(
            @RequestParam("type") Contract.ContractType type,
            @RequestParam("planet") String planet,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("reward") String reward,
            @RequestParam("comment") String comment
    ) {
        final Client client = getClientFromContext();

        Contract contract = new Contract();
        contract.setType(type);
        contract.setRequirements(description);
        contract.setPlanet(planet);
        contract.setTitle(title);
        contract.setReward(reward);
        contract.setComment(comment);
        contract.setId(UUID.randomUUID().toString());
        client.setContract(contract);
        clientStorage.save(client);

        return WebMapper.redirect(WebMapper.CONTRACTS);
    }

    private Client getClientFromContext() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return clientStorage.findByEmail(authentication.getName()).orElseThrow(NotAuthorizedException::new);
    }

    private String getRoleFromContext() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "").toUpperCase();
    }
}
