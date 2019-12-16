package io.uml.contracts.storage.impl;

import io.uml.contracts.model.dao.Tactic;
import io.uml.contracts.model.dao.Weapon;
import io.uml.contracts.repository.TacticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.12.2019
 */
@Component
public class TacticStorage extends BasicStorage<Tactic, String> {

    @Autowired
    public TacticStorage(TacticRepository repository) {
        super(repository);
    }
}
