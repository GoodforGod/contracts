package io.uml.contracts.model.dto;

import io.uml.contracts.model.dao.Song;

import java.util.UUID;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.12.2019
 */
public class SearchTrack {

    private String name;
    private String artist;
    private String url;

    public Song asSong() {
        final Song song = new Song();
        song.setName(name);
        song.setArtist(artist);
        song.setId(UUID.randomUUID().toString());
        return song;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getUrl() {
        return url;
    }
}
