package io.uml.contracts.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 12.09.2017
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class BannedException extends RuntimeException {
    public BannedException() {
        super("Too many failure login attempts.");
    }
}
