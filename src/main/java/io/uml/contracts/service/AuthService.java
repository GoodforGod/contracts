package io.uml.contracts.service;

import io.uml.contracts.controller.error.NotAuthorizedException;
import io.uml.contracts.model.dao.Client;
import io.uml.contracts.model.dao.Mercenary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 22.12.2019
 */
public interface AuthService {

    Optional<Client> getClientFromContext();

    Optional<Mercenary> getMercenaryFromContext();

    String getIdFromContext();

    String getRoleFromContext();
}
