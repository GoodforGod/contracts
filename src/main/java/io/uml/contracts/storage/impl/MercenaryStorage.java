package io.uml.contracts.storage.impl;

import io.uml.contracts.model.dao.Mercenary;
import io.uml.contracts.repository.MercenaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Component
public class MercenaryStorage extends BasicModifyStorage<Mercenary, String> {

    @Autowired
    public MercenaryStorage(MercenaryRepository repository) {
        super(repository);
    }
}
