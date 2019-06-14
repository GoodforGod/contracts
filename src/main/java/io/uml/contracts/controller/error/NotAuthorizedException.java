package io.uml.contracts.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 10.09.2017
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException() {
        super("Invalid Key.");
    }
}
