package io.uml.contracts.model.dao;

import javax.persistence.Entity;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Entity
public class Client extends BaseUuidModifyModel {

    private String name;
    private String surname;
    private String planet;

    public Client(String name, String surname, String planet) {
        this.name = name;
        this.surname = surname;
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
}
