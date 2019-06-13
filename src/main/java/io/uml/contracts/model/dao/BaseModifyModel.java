package io.uml.contracts.model.dao;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
public abstract class BaseModifyModel<ID extends Serializable> extends BaseModel<ID> {

    private Timestamp created;
    private Timestamp lastModify;

    public BaseModifyModel(ID id) {
        super(id);
        if (getCreated() == null)
            this.created = modify();
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
    public String toString() {
        return "BaseModifyModel{" +
                "created=" + created +
                ", lastModify=" + lastModify +
                "} " + super.toString();
    }
}
