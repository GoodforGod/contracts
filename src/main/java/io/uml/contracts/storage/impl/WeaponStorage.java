package io.uml.contracts.storage.impl;

import io.uml.contracts.model.dao.Weapon;
import io.uml.contracts.repository.WeaponLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Component
public class WeaponStorage extends BasicStorage<Weapon, String> {

    @Autowired
    public WeaponStorage(WeaponLogRepository repository) {
        super(repository);
    }
}
