package io.uml.contracts.controller;

import io.uml.contracts.controller.error.NotAuthorizedException;
import io.uml.contracts.model.dao.Client;
import io.uml.contracts.model.dao.Mercenary;
import io.uml.contracts.service.AuthService;
import io.uml.contracts.storage.impl.ClientStorage;
import io.uml.contracts.storage.impl.MercenaryStorage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 14.12.2019
 */
abstract class BaseWebController {

    final ClientStorage clientStorage;
    final MercenaryStorage mercenaryStorage;
    final AuthService authService;

    BaseWebController(AuthService authService, ClientStorage clientStorage, MercenaryStorage mercenaryStorage) {
        this.clientStorage = clientStorage;
        this.mercenaryStorage = mercenaryStorage;
        this.authService = authService;
    }

    Optional<Client> getClientFromContext() {
        return authService.getClientFromContext();
    }

    Optional<Mercenary> getMercenaryFromContext() {
        return authService.getMercenaryFromContext();
    }

    String getRoleFromContext() {
        return authService.getRoleFromContext();
    }
}
