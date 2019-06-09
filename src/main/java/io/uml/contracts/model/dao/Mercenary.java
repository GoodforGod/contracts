package io.uml.contracts.model.dao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Entity
public class Mercenary extends BaseUuidModifyModel {

    public enum MercenaryRoles {
        WARRIOR,
        WEAPONER,
        LEADER,
        COMBAT
    }

    private String name;
    private String surname;
    private String group = "Galaxy Guardians";
    private MercenaryRoles role;

    @ManyToMany(mappedBy = "participants", cascade = CascadeType.ALL)
    private Set<Flight> flights = new HashSet<>();

    @OneToMany(mappedBy = "responsible", cascade = CascadeType.ALL)
    private Set<WeaponLog> weaponLogs = new HashSet<>();

    public Mercenary(String name, String surname, MercenaryRoles role) {
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGroup() {
        return group;
    }

    public MercenaryRoles getRole() {
        return role;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public Flight addFlight(Flight flight) {
        this.flights.add(flight);
        return flight;
    }

    public Set<WeaponLog> getWeaponLogs() {
        return weaponLogs;
    }

    public WeaponLog addLog(WeaponLog weaponLog) {
        this.weaponLogs.add(weaponLog);
        return weaponLog;
    }
}
