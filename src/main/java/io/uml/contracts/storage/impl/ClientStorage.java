package io.uml.contracts.storage.impl;

import io.uml.contracts.model.dao.Client;
import io.uml.contracts.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Component
public class ClientStorage extends BasicStorage<Client, String> {

    @Autowired
    public ClientStorage(ClientRepository repository) {
        super(repository);
    }
}
