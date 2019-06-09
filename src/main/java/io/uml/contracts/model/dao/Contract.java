package io.uml.contracts.model.dao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Entity
public class Contract extends BaseUuidModifyModel {

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

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Flight flight;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Client client;
    private ContractPhase phase = ContractPhase.REQUIREMENTS;
    private String planet;
    private long reward = -1;
    private String requirements;
    private String comment;
    private boolean isApproved = false;

    public Contract(Client client, String planet) {
        this.client = client;
        this.planet = planet;
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

    public Client getClient() {
        return client;
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
}
