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
public class Mercenary implements Serializable {

    public enum MercenaryRoles {
        WARRIOR,
        WEAPONER,
        LEADER,
        COMBAT
    }

    @Id
    private String id;

    private String name;
    private String surname;
    private String group = "Galaxy Guardians";
    private MercenaryRoles role;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "flight_participants",
//            joinColumns = {@JoinColumn(name = "mercenary_id")},
//            inverseJoinColumns = {@JoinColumn(name = "flight_id")}
//    )
//    private Set<Flight> flights = new HashSet<>();
//
//    @OneToMany(mappedBy = "responsible", cascade = CascadeType.ALL)
//    private Set<CleaningLog> logs = new HashSet<>();
//
//    @OneToMany(mappedBy = "responsible", cascade = CascadeType.ALL)
//    private Set<WeaponLog> weaponLogs = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
//
//    public Set<CleaningLog> getLogs() {
//        return logs;
//    }
//
//    public Set<WeaponLog> getWeaponLogs() {
//        return weaponLogs;
//    }
//
//    public Set<Flight> getFlights() {
//        return flights;
//    }

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

//    public Flight addFlight(Flight flight) {
//        this.flights.add(flight);
//        return flight;
//    }
//
//    public WeaponLog addLog(WeaponLog weaponLog) {
//        this.weaponLogs.add(weaponLog);
//        return weaponLog;
//    }
//
//    public void setFlights(Set<Flight> flights) {
//        this.flights = flights;
//    }
//
//    public void setLogs(Set<CleaningLog> logs) {
//        this.logs = logs;
//    }
//
//    public void setWeaponLogs(Set<WeaponLog> weaponLogs) {
//        this.weaponLogs = weaponLogs;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setRole(MercenaryRoles role) {
        this.role = role;
    }
}
