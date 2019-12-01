package io.uml.contracts.storage.impl;

import io.uml.contracts.model.dao.Client;
import io.uml.contracts.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static io.uml.contracts.config.SecurityConfig.CLIENT_PASSWORD;
import static io.uml.contracts.config.SecurityConfig.CLIENT_UID;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Component
public class ClientStorage extends BasicStorage<Client, String> {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientStorage(ClientRepository repository) {
        super(repository);
        this.clientRepository = repository;
        findByEmail(CLIENT_UID).orElseGet(() -> {
            final Client client = new Client();
            client.setPlanet("Earth");
            client.setName("Tommy");
            client.setSurname("Lee");
            client.setEmail(CLIENT_UID);
            client.setPassword(CLIENT_PASSWORD);
            client.setId(UUID.randomUUID().toString());
            return save(client).orElse(null);
        });
    }

    public Optional<Client> findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
}
