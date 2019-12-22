package io.uml.contracts.controller;

import io.uml.contracts.ContractRunner;
import io.uml.contracts.config.WebMapper;
import io.uml.contracts.model.dao.Flight;
import io.uml.contracts.model.dao.Playlist;
import io.uml.contracts.model.dao.Song;
import io.uml.contracts.storage.impl.PlaylistStorage;
import io.uml.contracts.storage.impl.SongStorage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 22.12.2019
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class PlaylistControllerTests extends ContractRunner {

    @Autowired
    private PlaylistStorage playlistStorage;
    @Autowired
    private SongStorage songStorage;

    @Autowired
    private PlaylistWebController playlistWebController;

    @Test
    public void deleteValid() {
        final Playlist playlist = new Playlist();
        playlist.setId(UUID.randomUUID().toString());
        playlist.setName("Test");
        playlistStorage.save(playlist);

        final boolean exist = playlistStorage.exist(playlist.getId());
        assertTrue(exist);

        playlistWebController.deletePlaylist(playlist.getId());

        final boolean existAfter = playlistStorage.exist(playlist.getId());
        assertFalse(existAfter);
    }

    @Test
    public void deleteSongValid() {
        final Playlist playlist = new Playlist();
        playlist.setName("Test");
        playlist.setId(UUID.randomUUID().toString());

        final Song song = new Song();
        song.setId(UUID.randomUUID().toString());
        song.setName("Test Song");

        playlist.addSong(song);
        song.setPlaylist(playlist);

        playlistStorage.save(playlist);

        final boolean exist = playlistStorage.exist(playlist.getId());
        assertTrue(exist);
        final boolean existSong = songStorage.exist(song.getId());
        assertTrue(existSong);

        playlistWebController.deleteSong(playlist.getId(), song.getId());

        final Optional<Playlist> found = playlistStorage.find(playlist.getId());
        assertTrue(found.isPresent());
        assertTrue(found.get().getSongs().isEmpty());
    }

    @Test
    public void createPlaylistValid() {
        final String name = "Test Playlist";
        playlistWebController.createPlaylist(name);

        final List<Playlist> all = playlistStorage.findAll();
        assertFalse(all.isEmpty());

        final Optional<Playlist> found = all.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();

        assertTrue(found.isPresent());
    }

    @Test
    public void addSongManual() {
        final Playlist playlist = new Playlist();
        playlist.setName("Test");
        playlist.setId(UUID.randomUUID().toString());

        playlistStorage.save(playlist);

        final boolean exist = playlistStorage.exist(playlist.getId());
        assertTrue(exist);

        final String song = "Test Song";
        playlistWebController.addSong(song, "Test Artist", playlist.getId());

        final Optional<Playlist> found = playlistStorage.find(playlist.getId());
        assertTrue(found.isPresent());

        final List<Song> songs = found.get().getSongs();
        assertFalse(songs.isEmpty());

        final Optional<Song> songFound = songs.stream()
                .filter(s -> s.getName().equals(song))
                .findFirst();
        assertTrue(songFound.isPresent());
    }

    @Test
    public void addSongSimilar() {
        final Playlist playlist = new Playlist();
        playlist.setName("Test");
        playlist.setId(UUID.randomUUID().toString());

        playlistStorage.save(playlist);

        final boolean exist = playlistStorage.exist(playlist.getId());
        assertTrue(exist);

        playlistWebController.addSimilarSongs("Go All the Way", "The Raspberries", playlist.getId());

        final Optional<Playlist> found = playlistStorage.find(playlist.getId());
        assertTrue(found.isPresent());

        final List<Song> songs = found.get().getSongs();
        assertEquals(100, songs.size());
    }
}
