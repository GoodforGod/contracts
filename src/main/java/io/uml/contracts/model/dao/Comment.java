package io.uml.contracts.model.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Entity
@Table(name = "ccc_comment")
public class Comment implements Serializable {

    public enum Author {
        CLIENT,
        MERCENARY
    }

    @Id
    private String id;
    @Column(name = "comment_value")
    private String value;
    @Column(name = "comment_author")
    private String author;
    @Column(name = "author_value")
    private Author type;
    @Column(name = "comment_date")
    private Timestamp date = Timestamp.valueOf(LocalDateTime.now());

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tactic_id")
    private Tactic tactic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Tactic getTactic() {
        return tactic;
    }

    public void setTactic(Tactic tactic) {
        this.tactic = tactic;
    }

    public String getValue() {
        return value;
    }

    public String getAuthor() {
        return author;
    }

    public Author getType() {
        return type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setType(Author type) {
        this.type = type;
    }

    public Timestamp getDate() {
        return date;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
