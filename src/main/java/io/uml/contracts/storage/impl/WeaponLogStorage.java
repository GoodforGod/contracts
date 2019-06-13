package io.uml.contracts.storage.impl;

import io.uml.contracts.model.dao.WeaponLog;
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
public class WeaponLogStorage extends BasicStorage<WeaponLog, String> {

    @Autowired
    public WeaponLogStorage(WeaponLogRepository repository) {
        super(repository);
    }
}
