package ru.ifmo.exam.jpa.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;

@Getter
@ToString(callSuper = true, exclude = "teams")
//@EqualsAndHashCode использую от Object - сравнение ссылок

@Entity
public class Climber extends BaseId {
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String addr;

    @ManyToMany(mappedBy = "climbers")
    private HashSet<Team> teams;

    public Climber(){
        teams = new HashSet<>();
    }

    public Climber(String name, String addr) {
        setName(name);
        setAddr(addr);
        teams = new HashSet<>();
    }

    public void setName (String name) {
        if (name == null || name.length() < 3) {
            throw new IllegalArgumentException ("Имя альпиниста должно быть не менее 3х символов");
        }
        this.name = name;
    }
    public void setAddr (String addr) {
        if (addr == null || addr.length() < 5) {
            throw new IllegalArgumentException ("Адрес проживания альпиниста должен содержать не менее 5ти символов");
        }
        this.addr = addr;
    }

}
