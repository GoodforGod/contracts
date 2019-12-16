package io.uml.contracts.storage.impl;

import io.uml.contracts.model.dao.TacticRole;
import io.uml.contracts.repository.TacticRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.12.2019
 */
@Component
public class TacticRoleStorage extends BasicStorage<TacticRole, String> {

    @Autowired
    public TacticRoleStorage(TacticRoleRepository repository) {
        super(repository);
    }
}
