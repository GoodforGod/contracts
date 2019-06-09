package io.uml.contracts.model.dao;

import java.io.Serializable;
import java.util.Objects;

/**
 * Base Model for other services
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
public abstract class BaseModel<ID extends Serializable> {

    private ID id;

    public BaseModel(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseModel)) return false;
        BaseModel<?> baseModel = (BaseModel<?>) o;
        return Objects.equals(id, baseModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "id=" + id +
                '}';
    }
}
