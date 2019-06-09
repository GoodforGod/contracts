package io.uml.contracts.storage.impl;

import io.uml.contracts.model.dao.CleaningLog;
import io.uml.contracts.repository.CleaningLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Component
public class CleaningLogStorage extends BasicModifyStorage<CleaningLog, String> {

    @Autowired
    public CleaningLogStorage(CleaningLogRepository repository) {
        super(repository);
    }
}
