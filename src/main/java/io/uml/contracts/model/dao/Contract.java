package io.uml.contracts.model.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Entity
@Table(name = "ccc_contract")
public class Contract implements Serializable {

    public enum ContractType {
        ELIMINATION,
        ESCAPE,
        CAPTURE,
        CUSTOM
    }

    public enum ContractPhase {
        AWAITS,
        APPROVED
    }

    @Id
    private String id;

    private String title;
    @Column(name = "contract_phase")
    private ContractPhase phase = ContractPhase.AWAITS;
    private String planet;
    private String reward;
    private String requirements;
    @Column(name = "contract_comment")
    private String comment;
    @Column(name = "contract_type")
    private ContractType type;

    @OneToOne(mappedBy = "contract", cascade = CascadeType.ALL)
    private Flight flight;
    @OneToOne(mappedBy = "contract", cascade = CascadeType.ALL)
    private Client client;
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ContractType getType() {
        return type;
    }

    public void setType(ContractType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public ContractPhase getPhase() {
        return phase;
    }

    public void approve() {
        this.phase = ContractPhase.APPROVED;
    }

    public String getPlanet() {
        return planet;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getRequirements() {
        return requirements;
    }

    public String getComment() {
        return comment;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
