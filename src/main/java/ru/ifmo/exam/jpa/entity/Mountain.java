package ru.ifmo.exam.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;

@Getter
@ToString(exclude = "teams")

@Entity
public class Mountain{
    @Id
    @Column(length = 25, nullable = false)
    private String name;
    @Column(length = 25, nullable = false)
    private String country;
    @Column(nullable = false)
    private double height;

    @OneToMany(mappedBy = "mountain", orphanRemoval = true)
    private HashSet<Team> teams;

    public Mountain() {teams = new HashSet<>();}

    public Mountain(String name, String country, double height){
        setName(name);
        setCountry(country);
        setHeight(height);
        teams = new HashSet<>();
    };

    public void setName (String name) {
        if (name == null || name.length() < 4) {
            throw new IllegalArgumentException ("Название горы должно быть не менее 4х символов");
        }
        this.name = name;
    }
    public void setCountry (String country) {
        if (country == null || country.length() < 4) {
            throw new IllegalArgumentException ("Название горы должно быть не менее 4х символов");
        }
        this.country = country;
    }
    public void setHeight (double height) {
        if (height < 100.0) {
            throw new IllegalArgumentException ("Высота горы должна быть не менее 100");
        }
        this.height = height;
    }

    public void addTeam(Team team) {
        teams.add(team);
        team.setMountain(this);
    }
}
