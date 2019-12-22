package io.uml.contracts.model.dao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 1.12.2019
 */
@Entity
@Table(name = "ccc_playlists")
public class Playlist implements Serializable {

    @Id
    private String id;
    @NotNull
    private String name;
    private Timestamp created;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "mercenary_id")
    private Mercenary creator;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Song> songs = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Mercenary getCreator() {
        return creator;
    }

    public void setCreator(Mercenary creator) {
        this.creator = creator;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Playlist created() {
        if (this.created == null)
            this.created = Timestamp.valueOf(LocalDateTime.now());
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }
}
