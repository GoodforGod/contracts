package io.uml.contracts.model.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Entity
public class Flight implements Serializable {

    @Id
    private String id;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "contract_uid")
//    private Contract contract;
    private String route;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public Contract getContract() {
//        return contract;
//    }
//
//    public void setContract(Contract contract) {
//        this.contract = contract;
//    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
