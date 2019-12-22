package io.uml.contracts.controller;

import io.uml.contracts.config.SecurityConfig;
import io.uml.contracts.config.WebMapper;
import io.uml.contracts.controller.error.NotAuthorizedException;
import io.uml.contracts.controller.error.ResourceNotFoundException;
import io.uml.contracts.model.dao.Client;
import io.uml.contracts.model.dao.Comment;
import io.uml.contracts.model.dao.Contract;
import io.uml.contracts.model.dao.Mercenary;
import io.uml.contracts.service.AuthService;
import io.uml.contracts.storage.impl.ClientStorage;
import io.uml.contracts.storage.impl.ContractStorage;
import io.uml.contracts.storage.impl.MercenaryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.uml.contracts.config.TemplateMapper.*;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 13.06.2019
 */
@Controller
public class ContractWebController extends BaseWebController {

    private final ContractStorage contractStorage;

    @Autowired
    protected ContractWebController(ClientStorage clientStorage,
                                    MercenaryStorage mercenaryStorage,
                                    ContractStorage contractStorage,
                                    AuthService authService) {
        super(authService, clientStorage, mercenaryStorage);
        this.contractStorage = contractStorage;
    }

    @GetMapping("/")
    public String home() {
        return WebMapper.redirect(WebMapper.CONTRACT_TABLE);
    }

    private Contract verifyContract(String contractId) {
        Contract contract = contractStorage.find(contractId).orElseThrow(ResourceNotFoundException::new);
        String role = getRoleFromContext();
        try {
            if (!SecurityConfig.ROLE_LORD.equals(role)) {
                final Optional<Client> client = getClientFromContext();
                if (!client.isPresent() || !contract.getClient().getId().equals(client.get().getId())) {
                    throw new NotAuthorizedException();
                }
            }
        } catch (NotAuthorizedException e) {
            e.printStackTrace();
        }

        return contract;
    }

    @GetMapping(WebMapper.CONTRACT_TABLE + "/{id}")
    public ModelAndView viewContract(@PathVariable("id") String id) {
        final ModelAndView view = new ModelAndView(PAGE_CONTRACT_VIEW);
        final Contract contract = verifyContract(id);

        view.addObject("contract", contract);
        view.addObject("comments", contract.getComments());
        view.addObject("role", getRoleFromContext());
        return view;
    }

    @DeleteMapping(WebMapper.CONTRACT_TABLE + "/{id}")
    public String deleteContract(@PathVariable("id") String id) {
        final Contract contract = verifyContract(id);
        contract.setClient(null);
        contractStorage.delete(contract.getId());

        return WebMapper.redirect(WebMapper.CONTRACT_TABLE);
    }

    @GetMapping(WebMapper.CONTRACT_TABLE + "/approve/{id}")
    public ModelAndView approveContract(@PathVariable("id") String id) {
        final String role = getRoleFromContext();
        if (!SecurityConfig.ROLE_LORD.equals(role)) {
            throw new NotAuthorizedException();
        }

        Contract contract = contractStorage.find(id).orElseThrow(ResourceNotFoundException::new);
        contract.setPhase(Contract.ContractPhase.APPROVED);
        contractStorage.save(contract);

        return viewContract(id);
    }

    @GetMapping(WebMapper.CONTRACT_TABLE + "/init/{id}")
    public ModelAndView initiateContract(@PathVariable("id") String id) {
        final String role = getRoleFromContext();
        if (!SecurityConfig.ROLE_LORD.equals(role))
            throw new NotAuthorizedException();

        final Contract contract = contractStorage.find(id).orElseThrow(ResourceNotFoundException::new);
        contract.setPhase(Contract.ContractPhase.IN_PROGRESS);
        contractStorage.save(contract);

        return viewContract(id);
    }

    @GetMapping(WebMapper.CONTRACT_TABLE + "/close/{id}")
    public ModelAndView closeContract(@PathVariable("id") String id) {
        final String role = getRoleFromContext();
        if (!SecurityConfig.ROLE_LORD.equals(role))
            throw new NotAuthorizedException();

        final Contract contract = contractStorage.find(id).orElseThrow(ResourceNotFoundException::new);
        contract.setPhase(Contract.ContractPhase.CLOSED);
        contractStorage.save(contract);

        return viewContract(id);
    }

    @GetMapping(WebMapper.CONTRACT_TABLE)
    public ModelAndView getContracts() {
        final ModelAndView view = new ModelAndView(PAGE_CONTRACT_TABLE);
        final List<Contract> contracts = getContractsByRole();
        view.addObject("contracts", contracts);
        view.addObject("role", getRoleFromContext());
        return view;
    }

    private List<Contract> getContractsByRole() {
        return getClientFromContext()
                .map(c -> contractStorage.findByClient(c.getId()))
                .orElseGet(contractStorage::findAll);
    }

    @GetMapping(WebMapper.CONTRACT_ADD)
    public ModelAndView getContractAdd() {
        return new ModelAndView(PAGE_CONTRACT_ADD);
    }

    @PostMapping(WebMapper.CONTRACT_COMMENT + "/{id}")
    public String commentContract(@PathVariable("id") String contractId,
                                  @RequestParam("comment") String comment) {
        contractStorage.find(contractId).ifPresent(c -> {
            final Optional<Client> client = getClientFromContext();

            final Comment.AuthorType authorType = client.map(cli -> Comment.AuthorType.CLIENT)
                    .orElse(Comment.AuthorType.MERCENARY);

            final String name = client.map(Client::getName)
                    .orElseGet(() -> getMercenaryFromContext().map(m -> m.getName() + " " + m.getSurname())
                            .orElse(""));

            final Comment commentModel = new Comment();
            commentModel.setId(UUID.randomUUID().toString());
            commentModel.setDate(Timestamp.valueOf(LocalDateTime.now()));
            commentModel.setAuthorType(authorType);
            commentModel.setValue(comment);
            commentModel.setAuthor(name);
            commentModel.setContract(c);
            c.addComment(commentModel);

            contractStorage.save(c);
        });

        return WebMapper.redirect(WebMapper.CONTRACT_TABLE + "/" + contractId);
    }

    @PostMapping(WebMapper.CONTRACT_ADD)
    public String createContract(@RequestParam("type") Contract.ContractType type,
                                 @RequestParam("planet") String planet,
                                 @RequestParam("title") String title,
                                 @RequestParam("description") String description,
                                 @RequestParam("reward") String reward,
                                 @RequestParam("requirements") String requirements) {
        getClientFromContext().ifPresent(c -> {
            final Contract contract = new Contract();
            contract.setId(UUID.randomUUID().toString());
            contract.setType(type);
            contract.setRequirements(requirements);
            contract.setDescription(description);
            contract.setTitle(title);
            contract.setPlanet(planet);
            contract.setReward(reward);
            c.setContract(contract);
            contract.setClient(c);

            contractStorage.save(contract);
        });

        return WebMapper.redirect(WebMapper.CONTRACT_TABLE);
    }
}
