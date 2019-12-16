package io.uml.contracts.model.dao;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 1.12.2019
 */
@Entity
@Table(name = "ccc_tactic")
public class Tactic {

    @Id
    private String id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_uid")
    private Contract contract;

    @OneToMany(mappedBy = "tactic", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "tactic", cascade = CascadeType.ALL)
    private List<TacticRole> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<TacticRole> getRoles() {
        return roles;
    }

    public void setRoles(List<TacticRole> roles) {
        this.roles = roles;
    }
}
