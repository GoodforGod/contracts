package io.uml.contracts.controller;

import io.uml.contracts.controller.error.NotAuthorizedException;
import io.uml.contracts.model.dao.Client;
import io.uml.contracts.model.dao.Mercenary;
import io.uml.contracts.storage.impl.ClientStorage;
import io.uml.contracts.storage.impl.MercenaryStorage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 14.12.2019
 */
abstract class BaseWebController {

    final ClientStorage clientStorage;
    final MercenaryStorage mercenaryStorage;

    BaseWebController(ClientStorage clientStorage, MercenaryStorage mercenaryStorage) {
        this.clientStorage = clientStorage;
        this.mercenaryStorage = mercenaryStorage;
    }

    Client getClientFromContext() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return clientStorage.findByEmail(authentication.getName()).orElseThrow(NotAuthorizedException::new);
    }

    Mercenary getMercenaryFromContext() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return mercenaryStorage.findByEmail(authentication.getName()).orElseThrow(NotAuthorizedException::new);
    }

    String getIdFromContext() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return clientStorage.findByEmail(authentication.getName())
                .map(Client::getId)
                .orElseGet(() -> mercenaryStorage.findByEmail(authentication.getName())
                        .orElseThrow(NotAuthorizedException::new)
                        .getId());
    }

    String getRoleFromContext() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "").toUpperCase();
    }
}
