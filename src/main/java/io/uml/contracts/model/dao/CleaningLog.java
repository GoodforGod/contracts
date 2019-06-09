package io.uml.contracts.model.dao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Entity
public class CleaningLog extends BaseUuidModifyModel {

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Mercenary responsible;
    private int minutesSpend;

    public CleaningLog(int minutesSpend, Mercenary responsible) {
        this.minutesSpend = minutesSpend;
        this.responsible = responsible;
    }

    public int getMinutesSpend() {
        return minutesSpend;
    }

    public Mercenary getResponsible() {
        return responsible;
    }
}
