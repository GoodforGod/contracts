package io.uml.contracts.storage.impl;

import io.uml.contracts.model.dao.Flight;
import io.uml.contracts.model.dao.Playlist;
import io.uml.contracts.repository.FlightRepository;
import io.uml.contracts.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Component
public class PlaylistStorage extends BasicStorage<Playlist, String> {

    @Autowired
    public PlaylistStorage(PlaylistRepository repository) {
        super(repository);
    }
}
