package io.uml.contracts.model.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 1.12.2019
 */
@Entity
@Table(name = "ccc_song")
public class Song implements Serializable {

    @Id
    private String id;
    private String name;
    private String artist;

    /**
     * Seconds
     */
    private Integer duration;

    @ManyToMany
    @JoinTable(
            name = "ccc_song_to_playlist",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Playlist> playlists;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public Song setArtist(String author) {
        this.artist = author;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public Song setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Song addPlaylist(Playlist playlist) {
        if(this.playlists == null || this.playlists.isEmpty())
            this.playlists = new ArrayList<>();

        this.playlists.add(playlist);
        return this;
    }

}
