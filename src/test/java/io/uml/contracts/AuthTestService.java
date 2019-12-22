package io.uml.contracts;

import io.uml.contracts.model.dao.Client;
import io.uml.contracts.model.dao.Mercenary;
import io.uml.contracts.service.AuthService;
import io.uml.contracts.storage.impl.ClientStorage;
import io.uml.contracts.storage.impl.MercenaryStorage;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 22.12.2019
 */
@Profile("test")
@Primary
@Service
public class AuthTestService implements AuthService {

    private final MercenaryStorage mercenaryStorage;
    private final ClientStorage clientStorage;

    public AuthTestService(MercenaryStorage mercenaryStorage, ClientStorage clientStorage) {
        this.mercenaryStorage = mercenaryStorage;
        this.clientStorage = clientStorage;
    }

    @Override
    public Optional<Client> getClientFromContext() {
        return clientStorage.findByEmail("client");
    }

    @Override
    public Optional<Mercenary> getMercenaryFromContext() {
        return mercenaryStorage.findByEmail("lord");
    }

    @Override
    public String getIdFromContext() {
        return null;
    }

    @Override
    public String getRoleFromContext() {
        return "LORD";
    }
}
