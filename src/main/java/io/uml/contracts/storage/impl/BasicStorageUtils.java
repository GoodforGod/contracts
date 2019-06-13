package io.uml.contracts.storage.impl;

import io.uml.contracts.model.dao.BaseModel;

import java.io.Serializable;
import java.util.Collection;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
abstract class BasicStorageUtils<T, ID extends Serializable> {

    boolean isValid(ID id) {
        return id != null;
    }

    boolean isValid(T t) {
        return t != null;
    }

    boolean isValid(Collection<T> ts) {
        return ts != null && !ts.isEmpty();
    }
}
