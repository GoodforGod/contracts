package io.uml.contracts.service;

import io.uml.contracts.model.dto.SearchTrack;
import io.uml.contracts.model.dto.SimilarTrack;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.12.2019
 */
@Service
public class LastfmService {

    private final String server = "";
    private final RestTemplate template;
    private final HttpHeaders headers;

    public LastfmService() {
        this.template = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.add("Content-Type", "application/json");
        this.headers.add("Accept", "*/*");
    }

    public List<SimilarTrack> getSimilarTracks(String artist, String song) {
        final String uri = server;
        final HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        final ResponseEntity<String> entity = template.exchange(uri,
                HttpMethod.GET,
                requestEntity,
                String.class);

        return Collections.emptyList();
    }

    public List<SearchTrack> searchTracks(String song) {
        return Collections.emptyList();
    }
}
