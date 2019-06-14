package io.uml.contracts.storage.impl;

import io.uml.contracts.model.dao.Contract;
import io.uml.contracts.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Component
public class ContractStorage extends BasicStorage<Contract, String> {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractStorage(ContractRepository repository) {
        super(repository);
        this.contractRepository = repository;
    }

    public List<Contract> findByClient(String clientId) {
        return contractRepository.findAllByClient_Id(clientId);
    }
}
