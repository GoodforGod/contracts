package io.uml.contracts.model.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Entity
@Table(name = "ccc_cleaning_log")
public class CleaningLog implements Serializable {

    @Id
    private String id;

    private Timestamp cleanDate;
    private String description;

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

    public Timestamp getCleanDate() {
        return cleanDate;
    }

    public boolean isPast() {
        final LocalDate now = LocalDateTime.now().toLocalDate();
        return cleanDate.toLocalDateTime().toLocalDate().isBefore(now);
    }

    public void setCleanDate(Timestamp date) {
        this.cleanDate = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
