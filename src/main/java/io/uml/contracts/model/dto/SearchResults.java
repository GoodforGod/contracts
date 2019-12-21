package io.uml.contracts.model.dto;

import java.util.Collections;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.12.2019
 */
public class SearchResults {

    private List<SearchTrack> results = Collections.emptyList();

    public List<SearchTrack> getResults() {
        return results;
    }
}
