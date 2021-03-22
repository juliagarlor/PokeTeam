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
    @OneToMany(mappedBy = "team")
    private List<Pokemon> teamMates;

//    Constructors

    public Team() {
    }

//    When creating a new team, the list of team mates is empty
    public Team(Trainer trainerId) {
        this.trainerId = trainerId;
        this.teamMates = new ArrayList<>();
    }

//    Getters and Setters

    public List<Long> getTeamMatesIds(){
        List<Long> output = new ArrayList<>();
        for (Pokemon mate : teamMates){
            output.add(mate.getId());
        }
        return output;
    }

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

    public List<Pokemon> getTeamMates() {
        return teamMates;
    }

    public void setTeamMates(List<Pokemon> teamMates) {
        this.teamMates = teamMates;
    }
}
