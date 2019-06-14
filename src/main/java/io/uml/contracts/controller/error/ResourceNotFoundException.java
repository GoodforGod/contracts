package io.uml.contracts.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 10.09.2017
 */
@ResponseStatus(HttpStatus.NO_CONTENT)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Such item was not found.");
    }
}
