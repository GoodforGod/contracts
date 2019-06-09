package io.uml.contracts.model.dao;

import javax.persistence.Entity;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Entity
public class Comment extends BaseUuidModifyModel {

    public enum Author {
        CLIENT,
        MERCENARY
    }

    private String value;
    private String author;
    private Author type;

    public Comment(String value, String author, Author type) {
        this.value = value;
        this.author = author;
        this.type = type;
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
}
