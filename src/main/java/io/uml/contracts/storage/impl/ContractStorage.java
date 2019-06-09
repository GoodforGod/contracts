package io.uml.contracts.storage.impl;

import io.uml.contracts.model.dao.Contract;
import io.uml.contracts.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Component
public class ContractStorage extends BasicModifyStorage<Contract, String> {

    @Autowired
    public ContractStorage(ContractRepository repository) {
        super(repository);
    }
}
