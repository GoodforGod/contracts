package io.uml.contracts.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.uml.contracts.model.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.12.2019
 */
@Service
public class LastfmService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final String key;
    private final String server = "http://ws.audioscrobbler.com/2.0/";
    private final RestTemplate template;
    private final HttpHeaders headers;
    private final ObjectMapper mapper;

    public LastfmService(@Value("${lastfm.key}") String key, ObjectMapper mapper) {
        this.key = "&api_key=" + key + "&format=json";
        this.mapper = mapper;
        this.template = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.add("Content-Type", "application/json");
        this.headers.add("Accept", "*/*");
    }

    private ResponseEntity<String> getExchange(String uri) {
        final HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        return template.exchange(uri,
                HttpMethod.GET,
                requestEntity,
                String.class);
    }

    public List<SimilarTrack> getSimilarTracks(String artist, String song) {
        try {
            final String uri = server + "?method=track.getsimilar&artist=" + artist + "&track=" + song + key;
            final ResponseEntity<String> entity = getExchange(uri);
            return Optional.ofNullable(mapper.readValue(entity.getBody(), SimilarResults.class))
                    .flatMap(r -> Optional.ofNullable(r.getSimilartracks()).map(SimilarTracks::getTrack))
                    .orElse(Collections.emptyList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<SearchTrack> searchTracks(String song) {
        try {
            final String uri = server + "?method=track.search&track=" + song + key;
            final ResponseEntity<String> entity = getExchange(uri);
            return Optional.ofNullable(mapper.readValue(entity.getBody(), SearchResults.class))
                    .map(r -> r.getResults().getTrackmatches().getTrack())
                    .orElse(Collections.emptyList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }
}
