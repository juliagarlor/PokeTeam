package com.pokemon.pokemon.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.*;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainerId;
    @ElementCollection
    @CollectionTable(name = "team_mates")
    private List<Long> teamMates;

//    Constructors

    public Team() {
    }

    public Team(Trainer trainerId, List<Long> teamMates) {
        this.trainerId = trainerId;
        this.teamMates = teamMates;
    }

//    Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trainer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Trainer trainerId) {
        this.trainerId = trainerId;
    }

    public List<Long> getTeamMates() {
        return teamMates;
    }

    public void setTeamMates(List<Long> teamMates) {
        this.teamMates = teamMates;
    }
}
