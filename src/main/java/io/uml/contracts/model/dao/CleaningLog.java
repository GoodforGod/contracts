package io.uml.contracts.model.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Entity
public class CleaningLog implements Serializable {

    @Id
    private String id;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "mercenary_id")
//    private Mercenary responsible;
    private int minutesSpend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
//
//    public void setResponsible(Mercenary responsible) {
//        this.responsible = responsible;
//    }
//
//    public Mercenary getResponsible() {
//        return responsible;
//    }

    public int getMinutesSpend() {
        return minutesSpend;
    }

    public void setMinutesSpend(int minutesSpend) {
        this.minutesSpend = minutesSpend;
    }
}
