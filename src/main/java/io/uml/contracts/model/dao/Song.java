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

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

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

    public Playlist getPlaylist() {
        return playlist;
    }

    public Song setPlaylist(Playlist playlist) {
        this.playlist = playlist;
        return this;
    }
}
