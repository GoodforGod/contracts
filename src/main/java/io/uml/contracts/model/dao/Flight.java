package io.uml.contracts.model.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Entity
@Table(name = "ccc_flight")
public class Flight implements Serializable {

    @Id
    private String id;
    private String routeDetails;
    private String planets;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_uid")
    private Contract contract;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<String> getPlanetsList() {
        return Arrays.asList(planets.split(","));
    }

    public String getRouteDetails() {
        return routeDetails;
    }

    public void setRouteDetails(String routeDetails) {
        this.routeDetails = routeDetails;
    }

    public String getPlanets() {
        return planets;
    }

    public void setPlanets(String planets) {
        this.planets = planets;
    }
}
