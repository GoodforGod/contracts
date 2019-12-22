package io.uml.contracts.controller;

import io.uml.contracts.config.WebMapper;
import io.uml.contracts.model.dao.Playlist;
import io.uml.contracts.model.dao.Song;
import io.uml.contracts.service.LastfmService;
import io.uml.contracts.storage.impl.ClientStorage;
import io.uml.contracts.storage.impl.MercenaryStorage;
import io.uml.contracts.storage.impl.PlaylistStorage;
import io.uml.contracts.storage.impl.SongStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.uml.contracts.config.TemplateMapper.*;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.12.2019
 */
@Controller
public class PlaylistWebController extends BaseWebController {

    private final PlaylistStorage playlistStorage;
    private final SongStorage songStorage;
    private final LastfmService lastfmService;

    @Autowired
    public PlaylistWebController(ClientStorage clientStorage,
                                 MercenaryStorage mercenaryStorage,
                                 PlaylistStorage playlistStorage,
                                 SongStorage songStorage,
                                 LastfmService lastfmService) {
        super(clientStorage, mercenaryStorage);
        this.playlistStorage = playlistStorage;
        this.songStorage = songStorage;
        this.lastfmService = lastfmService;
    }

    @GetMapping(WebMapper.PLAYLIST_TABLE + "/{id}")
    public ModelAndView viewPlaylist(@PathVariable("id") String id) {
        final ModelAndView view = new ModelAndView(PAGE_PLAYLIST_VIEW);
        final Playlist playlist = playlistStorage.find(id).orElse(null);
        if (playlist == null)
            return new ModelAndView(PAGE_PLAYLIST_TABLE);

        view.addObject("playlist", playlist);
        view.addObject("role", getRoleFromContext());
        return view;
    }

    @DeleteMapping(WebMapper.PLAYLIST_TABLE + "/{id}")
    public String deletePlaylist(@PathVariable("id") String id) {
        playlistStorage.delete(id);
        return WebMapper.redirect(WebMapper.PLAYLIST_TABLE);
    }

    @DeleteMapping(WebMapper.PLAYLIST_TABLE + "/{playlistId}" + "/song/{songId}")
    public String deleteSong(@PathVariable("playlistId") String playlistId,
                                 @PathVariable("songId") String songId) {
        playlistStorage.find(playlistId).ifPresent(p -> {
            final List<Song> songs = p.getSongs().stream()
                    .filter(s -> !s.getId().equals(songId))
                    .collect(Collectors.toList());
            p.setSongs(songs);
            playlistStorage.save(p);
        });

        return WebMapper.redirect(WebMapper.PLAYLIST_TABLE + "/" + playlistId);
    }

    @GetMapping(WebMapper.PLAYLIST_TABLE)
    public ModelAndView getPlaylists() {
        final ModelAndView view = new ModelAndView(PAGE_PLAYLIST_TABLE);
        final List<Playlist> playlists = playlistStorage.findAll();
        view.addObject("playlists", playlists);
        view.addObject("role", getRoleFromContext());
        return view;
    }

    @GetMapping(WebMapper.PLAYLIST_ADD)
    public ModelAndView getPlaylistAdd() {
        return new ModelAndView(PAGE_PLAYLIST_ADD);
    }

    @PostMapping(WebMapper.PLAYLIST_ADD)
    public String createPlaylist(@RequestParam("name") String name) {
        final String uri = getMercenaryFromContext().map(m -> {
            final Playlist playlist = new Playlist();
            playlist.setId(UUID.randomUUID().toString());
            playlist.setCreator(m);
            playlist.setName(name);
            return playlistStorage.save(playlist).orElse(null);
        }).filter(Objects::nonNull)
                .map(r -> WebMapper.PLAYLIST_TABLE + "/" + r.getId())
                .orElse(WebMapper.PLAYLIST_TABLE);

        return WebMapper.redirect(uri);
    }

    @PostMapping(WebMapper.SONG_ADD)
    public String addSong(@RequestParam("song") String name,
                          @RequestParam("artist") String artist,
                          @RequestParam("playlistId") String playlistId) {
        playlistStorage.find(playlistId).ifPresent(p -> {
            final Song song = new Song();
            song.setId(UUID.randomUUID().toString());
            song.setName(name);
            song.setArtist(artist);
            p.addSong(song);
            song.addPlaylist(p);

            playlistStorage.save(p);
        });

        return WebMapper.redirect(WebMapper.PLAYLIST_TABLE + "/" + playlistId);
    }

    @PostMapping(WebMapper.SONG_SIMILAR)
    public String addSimilarSongs(@RequestParam("song") String name,
                                  @RequestParam("artist") String artist,
                                  @RequestParam("playlistId") String playlistId) {
        playlistStorage.find(playlistId).ifPresent(p -> {
            lastfmService.searchTracks(name).stream().findFirst()
                    .ifPresent(s -> lastfmService.getSimilarTracks(s.getArtist(), s.getName()).forEach(song -> {
                        song.addPlaylist(p);
                        p.addSong(song);
                    }));

            playlistStorage.save(p);
        });

        return WebMapper.redirect(WebMapper.PLAYLIST_TABLE + "/" + playlistId);
    }
}
