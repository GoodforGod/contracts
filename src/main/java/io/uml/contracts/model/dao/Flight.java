package io.uml.contracts.model.dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Entity
public class Flight extends BaseUuidModifyModel {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_uid")
    private Contract contract;
    private List<String> route = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "participants",
            joinColumns = {@JoinColumn(name = "mercenary_id")},
            inverseJoinColumns = {@JoinColumn(name = "flight_id")}
    )
    private List<Mercenary> participants = new ArrayList<>();

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<String> getRoute() {
        return route;
    }

    public List<String> addRoute(String planet) {
        this.route.add(planet);
        return route;
    }

    public List<Mercenary> getParticipants() {
        return participants;
    }

    public List<Mercenary> addParticipant(Mercenary mercenary) {
        this.participants.add(mercenary);
        return participants;
    }
}
