package io.uml.contracts.service;

import io.uml.contracts.controller.error.NotAuthorizedException;
import io.uml.contracts.model.dao.Client;
import io.uml.contracts.model.dao.Mercenary;
import io.uml.contracts.storage.impl.ClientStorage;
import io.uml.contracts.storage.impl.MercenaryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 22.12.2019
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final ClientStorage clientStorage;
    private final MercenaryStorage mercenaryStorage;

    @Autowired
    public AuthServiceImpl(ClientStorage clientStorage, MercenaryStorage mercenaryStorage) {
        this.clientStorage = clientStorage;
        this.mercenaryStorage = mercenaryStorage;
    }

    @Override
    public Optional<Client> getClientFromContext() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return clientStorage.findByEmail(authentication.getName());
    }

    @Override
    public Optional<Mercenary> getMercenaryFromContext() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String name = (authentication == null) ? "lord" : authentication.getName();

        return mercenaryStorage.findByEmail(name);
    }

    @Override
    public String getIdFromContext() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return clientStorage.findByEmail(authentication.getName())
                .map(Client::getId)
                .orElseGet(() -> mercenaryStorage.findByEmail(authentication.getName())
                        .orElseThrow(NotAuthorizedException::new)
                        .getId());
    }

    @Override
    public String getRoleFromContext() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "").toUpperCase();
    }
}
