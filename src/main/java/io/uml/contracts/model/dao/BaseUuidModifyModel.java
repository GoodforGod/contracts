package io.uml.contracts.model.dao;

import java.util.UUID;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
public abstract class BaseUuidModifyModel extends BaseModifyModel<String> {

    public BaseUuidModifyModel() {
        super(UUID.randomUUID().toString());
    }
}
