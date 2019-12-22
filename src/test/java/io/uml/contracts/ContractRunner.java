package io.uml.contracts;

import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 22.12.2019
 */
public abstract class ContractRunner extends Assert {

    protected final RestTemplate template = new RestTemplate();
    protected final HttpHeaders headers;

    protected ContractRunner() {
        this.headers = new HttpHeaders();
        this.headers.add("Content-Type", "application/json");
        this.headers.add("Accept", "*/*");
    }

    protected String base() {
        return "http://localhost:8080";
    }

    protected ResponseEntity<String> execute(String uri, HttpMethod method) {
        return execute(uri, method, "");
    }

    protected ResponseEntity<String> execute(String uri, HttpMethod method, String body) {
        final HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        return template.exchange(uri,
                method,
                requestEntity,
                String.class);
    }
}
