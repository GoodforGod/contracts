package io.uml.contracts.model.dao;

import javax.persistence.*;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.12.2019
 */
@Entity
@Table(name = "ccc_tactic_role")
public class TacticRole {

    public enum TacticRoleTypes {
        ATTACK,
        DEFENCE,
        RANGE,
        SUPPORT
    }

    @Id
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tactic_id")
    private Tactic tactic;

    private TacticRoleTypes roleType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mercenary_id")
    private Mercenary mercenary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tactic getTactic() {
        return tactic;
    }

    public void setTactic(Tactic tactic) {
        this.tactic = tactic;
    }

    public TacticRoleTypes getRoleType() {
        return roleType;
    }

    public void setRoleType(TacticRoleTypes roleType) {
        this.roleType = roleType;
    }

    public Mercenary getMercenary() {
        return mercenary;
    }

    public void setMercenary(Mercenary mercenary) {
        this.mercenary = mercenary;
    }
}
