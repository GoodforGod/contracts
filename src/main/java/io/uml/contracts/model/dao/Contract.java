package io.uml.contracts.model.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        WAIT_APPROVE,
        APPROVED,
        IN_PROGRESS,
        CLOSED
    }

    @Id
    private String id;
    private String title;

    @Column(name = "contract_phase")
    private ContractPhase phase = ContractPhase.WAIT_APPROVE;
    private String planet;
    private String reward;
    private String requirements;
    private String description;

    @Column(name = "contract_type")
    private ContractType type;

    @OneToOne(mappedBy = "contract", cascade = CascadeType.ALL)
    private Flight flight;

    @OneToOne(mappedBy = "contract", cascade = CascadeType.ALL)
    private Tactic tactic;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_uid")
    private Client client;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public void setPhase(ContractPhase phase) {
        this.phase = phase;
    }

    public Tactic getTactic() {
        return tactic;
    }

    public void setTactic(Tactic tactic) {
        this.tactic = tactic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Contract addComment(Comment comment) {
        if(comments == null)
            this.comments = new ArrayList<>();
        this.comments.add(comment);
        return this;
    }

    public void setComments(List<Comment> comments) {
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

    public String getDescription() {
        return description;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public void setDescription(String comment) {
        this.description = comment;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
