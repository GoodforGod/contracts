package io.uml.contracts.model.dao;

import java.util.UUID;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
public class BaseUuidModel extends BaseModel<String> {

    public BaseUuidModel() {
        super(UUID.randomUUID().toString());
    }
}
