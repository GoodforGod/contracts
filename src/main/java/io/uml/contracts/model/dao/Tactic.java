package io.uml.contracts.model.dao;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(mappedBy = "approvedTactics")
    private List<Mercenary> approved;

    @OneToMany(mappedBy = "tactic", cascade = CascadeType.ALL)
    private List<Comment> comments;

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
}
