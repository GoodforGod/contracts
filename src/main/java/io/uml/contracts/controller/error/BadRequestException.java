package io.uml.contracts.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 10.09.2017
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("Invalid parameters.");
    }
}
