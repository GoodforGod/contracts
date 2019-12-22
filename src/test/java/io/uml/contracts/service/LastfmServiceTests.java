package io.uml.contracts.service;

import io.uml.contracts.ContractRunner;
import io.uml.contracts.model.dao.Song;
import io.uml.contracts.model.dto.SearchTrack;
import io.uml.contracts.model.dto.SimilarTrack;
import io.uml.contracts.service.LastfmService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 22.12.2019
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class LastfmServiceTests extends ContractRunner {

    @Autowired
    private LastfmService lastfmService;

    @Test
    public void similarFound() {
        final List<Song> tracks = lastfmService.getSimilarTracks("The Raspberries", "Go All the Way");
        assertNotNull(tracks);
        assertEquals(100, tracks.size());
    }

    @Test
    public void searchTracks() {
        final List<Song> tracks = lastfmService.searchTracks("Go All the Way");
        assertNotNull(tracks);
        assertEquals(30, tracks.size());
    }
}
