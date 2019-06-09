package io.uml.contracts.model.dao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Entity
public class WeaponLog extends BaseUuidModifyModel {

    public enum WeaponStatus {
        BAD,
        MIDDLE,
        GOOD
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mercenary_id")
    private Mercenary responsible;
    private String weapon;
    private WeaponStatus status;

    public WeaponLog(Mercenary responsible, String weapon, WeaponStatus status) {
        this.responsible = responsible;
        this.weapon = weapon;
        this.status = status;
    }

    public Mercenary getResponsible() {
        return responsible;
    }

    public String getWeapon() {
        return weapon;
    }

    public WeaponStatus getStatus() {
        return status;
    }
}
