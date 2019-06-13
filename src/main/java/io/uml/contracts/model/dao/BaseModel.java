package io.uml.contracts.model.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Base Model for other services
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
public abstract class BaseModel<ID extends Serializable> {

    private ID id;
    private Timestamp created;
    private Timestamp lastModify;

    public BaseModel(ID id) {
        this.id = id;
        this.created = Timestamp.valueOf(LocalDateTime.now());
    }

    public ID getId() {
        return id;
    }

    void setId(ID id) {
        this.id = id;
    }

    void setCreated(Timestamp created) {
        this.created = created;
    }

    void setLastModify(Timestamp lastModify) {
        this.lastModify = lastModify;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getLastModify() {
        return lastModify;
    }

    public Timestamp modify() {
        this.lastModify = Timestamp.valueOf(LocalDateTime.now());
        return this.lastModify;
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
