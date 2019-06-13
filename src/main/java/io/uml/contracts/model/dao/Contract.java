package io.uml.contracts.model.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Entity
public class Contract implements Serializable {

    public enum ContractType {
        ELIMINATION,
        ESCAPE,
        CAPTURE,
        CUSTOM
    }

    public enum ContractPhase {
        REQUIREMENTS,
        REWARD,
        DONE
    }

    @Id
    private String id;

//    @OneToOne(mappedBy = "contract", cascade = CascadeType.ALL)
//    private Flight flight;
    @OneToOne(mappedBy = "contract", cascade = CascadeType.ALL)
    private Client client;

    private String title;
    private ContractPhase phase = ContractPhase.REQUIREMENTS;
    private String planet;
    private long reward = -1;
    private String requirements;
    private String comment;
    private boolean isApproved = false;
    private ContractType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

//    public Flight getFlight() {
//        return flight;
//    }
//
//    public void setFlight(Flight flight) {
//        this.flight = flight;
//    }

    public ContractPhase getPhase() {
        return phase;
    }

    public String getPlanet() {
        return planet;
    }

    public long getReward() {
        return reward;
    }

    public void setReward(long reward) {
        this.reward = reward;
    }

    public String getRequirements() {
        return requirements;
    }

    public String getComment() {
        return comment;
    }

    public void setPhase(ContractPhase phase) {
        this.phase = phase;
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

    public boolean isApproved() {
        return isApproved;
    }

    public void approve() {
        this.isApproved = true;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
