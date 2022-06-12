package ru.ifmo.exam.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;

@Getter
@EqualsAndHashCode(callSuper = true, exclude = {"active","climbers","maxClimbers","mountain"})
@ToString(callSuper = true)

@Entity
public class Team extends BaseId{
    private boolean active = true; // true - набор открыт

    // при добавлении группы в таблицу, если в группе есть гора и альпинисты,
    // они должны быть сразу добавлены в свои таблицы, горы с одинаковым названием добавляться не должны
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name ="climbers_teams", joinColumns = @JoinColumn(name ="id_team"),
            inverseJoinColumns = @JoinColumn(name = "id_climber"))
    private HashSet<Climber> climbers; //заменила массив на лист

    @Setter
    @NonNull
    @Column(name = "max_climbers")
    private int maxClimbers = 3; // ограничила колличество участников

    @Setter
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Mountain mountain;


    public Team(){
        climbers = new HashSet<>();
    }

    public Team(int maxClimbers){
        this.maxClimbers = maxClimbers;
        climbers = new HashSet<>();
    }

    public void addClimber(Climber climber) {
        if (!active) {
            System.out.println("Набор в группу закрыт");
            return;
        }
        if (climbers.size() <= maxClimbers) {
            if (climbers.add(climber)) climber.getTeams().add(this);
            // если в HashSet альпиниста удалось положить новую группу, имеет смысл добавлять альпиниста в группу
        }
        if (climbers.size() == maxClimbers) active = false;
    }

}
