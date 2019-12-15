package io.uml.contracts.model.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Entity
@Table(name = "ccc_weapons")
public class Weapon implements Serializable {

    public enum WeaponStatus {
        BAD,
        MIDDLE,
        GOOD
    }

    public enum WeaponType {
        BLASTER,
        FIRE_ARM,
        MACHINE_GUN,
        RIFLE,
        DAGGER,
        SWORD,
        MACE,
        UNIQUE
    }

    @Id
    private String id;
    private String name;
    private String description;
    private WeaponStatus status;
    private WeaponType type;
    private Timestamp addDate;

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

    public String getName() {
        return name;
    }

    public WeaponStatus getStatus() {
        return status;
    }

    public void setName(String weapon) {
        this.name = weapon;
    }

    public void setStatus(WeaponStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }

    public Timestamp getAddDate() {
        return addDate;
    }

    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }
}
