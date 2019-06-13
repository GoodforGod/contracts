package io.uml.contracts.model.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Entity
public class Comment implements Serializable {

    public enum Author {
        CLIENT,
        MERCENARY
    }

    @Id
    private String id;
    private String value;
    private String author;
    private Author type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
