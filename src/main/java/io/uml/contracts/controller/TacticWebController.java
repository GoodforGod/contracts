package io.uml.contracts.controller;

import io.uml.contracts.config.WebMapper;
import io.uml.contracts.controller.error.ResourceNotFoundException;
import io.uml.contracts.model.dao.Contract;
import io.uml.contracts.model.dao.Mercenary;
import io.uml.contracts.model.dao.Tactic;
import io.uml.contracts.model.dao.TacticRole;
import io.uml.contracts.storage.impl.ClientStorage;
import io.uml.contracts.storage.impl.ContractStorage;
import io.uml.contracts.storage.impl.MercenaryStorage;
import io.uml.contracts.storage.impl.TacticStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.uml.contracts.config.TemplateMapper.PAGE_TACTIC_ADD;
import static io.uml.contracts.config.TemplateMapper.PAGE_TACTIC_VIEW;
import static io.uml.contracts.config.WebMapper.redirect;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.12.2019
 */
@Controller
public class TacticWebController extends BaseWebController {

    private final TacticStorage tacticStorage;
    private final ContractStorage contractStorage;

    @Autowired
    public TacticWebController(ClientStorage clientStorage,
                               MercenaryStorage mercenaryStorage,
                               TacticStorage tacticStorage,
                               ContractStorage contractStorage) {
        super(clientStorage, mercenaryStorage);
        this.tacticStorage = tacticStorage;
        this.contractStorage = contractStorage;
    }

    @GetMapping(WebMapper.TACTIC_VIEW + "/{id}")
    public ModelAndView view(@PathVariable("id") String id) {
        final ModelAndView view = new ModelAndView(PAGE_TACTIC_VIEW);
        final Tactic tactic = tacticStorage.find(id).orElseThrow(ResourceNotFoundException::new);
        view.addObject("tactic", tactic);
        view.addObject("role", getRoleFromContext());
        return view;
    }

    @DeleteMapping(WebMapper.TACTIC_VIEW + "/{id}")
    public String delete(@PathVariable("id") String id) {
        final Optional<Tactic> flight = tacticStorage.find(id);
        final Optional<String> contractId = flight.map(f -> f.getContract().getId());
        flight.ifPresent(t -> {
            t.setContract(null);
            tacticStorage.save(t);
            tacticStorage.delete(t.getId());
        });

        return contractId.map(s -> redirect(WebMapper.CONTRACT_TABLE + "/" + s)).orElseGet(() -> redirect(WebMapper.CONTRACT_TABLE));
    }

    @GetMapping(WebMapper.TACTIC_ADD + "/{id}")
    public ModelAndView addPage(@PathVariable("id") String contractId) {
        final ModelAndView view = new ModelAndView(PAGE_TACTIC_ADD);
        final List<Mercenary> all = mercenaryStorage.findAll();
        final Contract contract = contractStorage.find(contractId).orElseThrow(() -> new IllegalArgumentException("Contract not found"));
        view.addObject("contract", contract);
        view.addObject("mercenaries", all);
        return view;
    }

    @PostMapping(WebMapper.TACTIC_ADD + "/" + "{contractId}")
    public String add(@PathVariable("contractId") String contractId,
                      @RequestParam("name") String name,
                      @RequestParam Map<String, String> params) {
        final Tactic tactic = new Tactic();
        final List<TacticRole> roles = mercenaryStorage.findAll().stream()
                .filter(m -> params.containsKey(m.getName()))
                .map(m -> {
                    final TacticRole role = new TacticRole();
                    role.setId(UUID.randomUUID().toString());
                    role.setMercenary(m);
                    role.setRoleType(TacticRole.TacticRoleTypes.valueOf(params.get(m.getName())));
                    role.setTactic(tactic);
                    return role;
                }).collect(Collectors.toList());

        tactic.setRoles(roles);
        final Contract contract = contractStorage.find(contractId).orElseThrow(() -> new IllegalArgumentException("Contract not found"));
        tactic.setId(UUID.randomUUID().toString());
        tactic.setName(name);
        tactic.setContract(contract);
        tacticStorage.save(tactic);

        return redirect(WebMapper.TACTIC_VIEW + "/" + tactic.getId());
    }
}
