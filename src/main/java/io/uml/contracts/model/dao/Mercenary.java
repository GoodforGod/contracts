package io.uml.contracts.model.dao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
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
@Table(name = "ccc_mercenary", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Mercenary implements Serializable {

    public enum MercenaryRoles {
        LEADER,
        WARRIOR,
        GUNSMITH,
        COMBAT
    }

    @Id
    private String id;

    private String name;
    private String surname;
    @NotNull
    private String email;
    private String password;
    private String clan = "Galaxy Guardians";
    private MercenaryRoles role;

    @ManyToMany
    @JoinTable(
            name = "ccc_approved_tactics",
            joinColumns = @JoinColumn(name = "tactic_id"),
            inverseJoinColumns = @JoinColumn(name = "mercenary_id"))
    private List<Tactic> approvedTactics;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ccc_flight_participants",
            joinColumns = {@JoinColumn(name = "mercenary_id")},
            inverseJoinColumns = {@JoinColumn(name = "flight_id")}
    )
    private Set<Flight> flights = new HashSet<>();

    @OneToMany(mappedBy = "responsible", cascade = CascadeType.ALL)
    private Set<CleaningLog> logs = new HashSet<>();

    @OneToMany(mappedBy = "responsible", cascade = CascadeType.ALL)
    private Set<WeaponLog> weaponLogs = new HashSet<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private Set<Playlist> playlists = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<Tactic> getApprovedTactics() {
        return approvedTactics;
    }

    public void setApprovedTactics(List<Tactic> approvedTactics) {
        this.approvedTactics = approvedTactics;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<CleaningLog> getLogs() {
        return logs;
    }

    public Set<WeaponLog> getWeaponLogs() {
        return weaponLogs;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getClan() {
        return clan;
    }

    public MercenaryRoles getRole() {
        return role;
    }

    public Flight addFlight(Flight flight) {
        this.flights.add(flight);
        return flight;
    }

    public WeaponLog addLog(WeaponLog weaponLog) {
        this.weaponLogs.add(weaponLog);
        return weaponLog;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    public void setLogs(Set<CleaningLog> logs) {
        this.logs = logs;
    }

    public void setWeaponLogs(Set<WeaponLog> weaponLogs) {
        this.weaponLogs = weaponLogs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public void setRole(MercenaryRoles role) {
        this.role = role;
    }
}
