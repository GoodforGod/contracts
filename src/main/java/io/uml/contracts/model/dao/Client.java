package io.uml.contracts.model.dao;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Entity
@Table(name = "ccc_client")
public class Client implements Serializable {

    @Id
    private String id;
    private String name;
    private String surname;
    private String planet;

    @Column(unique = true)
    private String email;
    private String password;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
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

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPlanet() {
        return planet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
