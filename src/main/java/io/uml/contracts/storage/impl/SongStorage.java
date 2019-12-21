package io.uml.contracts.storage.impl;

import io.uml.contracts.model.dao.Playlist;
import io.uml.contracts.model.dao.Song;
import io.uml.contracts.repository.PlaylistRepository;
import io.uml.contracts.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Component
public class SongStorage extends BasicStorage<Song, String> {

    @Autowired
    public SongStorage(SongRepository repository) {
        super(repository);
    }
}
