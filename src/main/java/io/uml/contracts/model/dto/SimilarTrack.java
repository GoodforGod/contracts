package io.uml.contracts.model.dto;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.12.2019
 */
public class SimilarTrack {

    private String name;
    private String mbid;
    private String url;
    private Integer duration;
    private SimilarArtist artist;

    public String getName() {
        return name;
    }

    public String getMbid() {
        return mbid;
    }

    public String getUrl() {
        return url;
    }

    public Integer getDuration() {
        return duration;
    }

    public SimilarArtist getArtist() {
        return artist;
    }
}
