package ru.ifmo.exam.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ru.ifmo.exam.jpa.entity.Climber;
import ru.ifmo.exam.jpa.entity.Mountain;
import ru.ifmo.exam.jpa.entity.Team;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        List<Climber> climbers = new ArrayList<>();
        climbers.add(new Climber("Иван", "Cанкт-Петербург"));
        climbers.add(new Climber("Сергей", "Нижний Новгород"));
        climbers.add(new Climber("Никита", "Cанкт-Петербург"));
        climbers.add(new Climber("Ярослав", "Москва"));
        climbers.add(new Climber("Иван", "Воронеж"));
        climbers.add(new Climber("Иван", "Воронеж"));

        List<Team> teams = new ArrayList<>(3);
        Team team = new Team();
        teams.add(team);
        new Mountain("Эльбрус", "Россия", 5000.0).addTeam(teams.get(0));
        while (team.getClimbers().size() < 3) {
            team.addClimber(climbers.get((int)(Math.random()*6)));
        }
        team = new Team();
        teams.add(team);
        new Mountain("Эверест", "Непал", 8000.0).addTeam(teams.get(1));
        while (team.getClimbers().size() < 2) {
            team.addClimber(climbers.get((int)(Math.random()*6)));
        }
        team = new Team(4);
        teams.add(team);
        new Mountain("Казбек", "Грузия", 4500.0).addTeam(teams.get(2));
        while (team.getClimbers().size() < 2) {
            team.addClimber(climbers.get((int)(Math.random()*6)));
        }



        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormExam4");
        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();
        for (Team group : teams) {
            manager.persist(group);
        }
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        for (Climber climber : climbers) { //добавляю тех, кто не попал в группы
            manager.persist(climber);
        }
        manager.getTransaction().commit();

        manager.close();
        factory.close();

        teams.forEach(System.out::println);


    }
}
