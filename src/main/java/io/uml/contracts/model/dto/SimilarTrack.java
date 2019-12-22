package io.uml.contracts.model.dto;

import io.uml.contracts.model.dao.Song;

import java.util.UUID;

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

    public Song asSong() {
        final Song song = new Song();
        song.setArtist(artist.getName());
        song.setDuration(duration);
        song.setId(UUID.randomUUID().toString());
        song.setName(name);
        return song;
    }

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
