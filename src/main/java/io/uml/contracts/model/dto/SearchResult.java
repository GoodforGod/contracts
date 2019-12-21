package io.uml.contracts.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.12.2019
 */
public class SearchResult {

    private SearchTracks trackmatches;

    public SearchTracks getTrackmatches() {
        return trackmatches;
    }
}
