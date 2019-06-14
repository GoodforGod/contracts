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
@Table(name = "ccc_weapon_log")
public class WeaponLog implements Serializable {

    public enum WeaponStatus {
        BAD,
        MIDDLE,
        GOOD
    }

    @Id
    private String id;
    private String weapon;
    private WeaponStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mercenary_id")
    private Mercenary responsible;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setResponsible(Mercenary responsible) {
        this.responsible = responsible;
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

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public void setStatus(WeaponStatus status) {
        this.status = status;
    }
}
