package com.pokemon.pokemon.utils.dtos;

import com.pokemon.pokemon.model.*;

import java.util.*;

public class TeamDTO {
    private Long id;
    private Long trainerId;
    private List<Long> teamMates;

//    Constructors

    public TeamDTO(Long trainerId, List<Long> teamMates) {
        this.trainerId = trainerId;
        this.teamMates = teamMates;
    }

    public TeamDTO(Team team) {
        this(team.getTrainerId().getId(), team.getTeamMatesIds());
        setId(team.getId());
    }

//    Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public List<Long> getTeamMates() {
        return teamMates;
    }

    public void setTeamMates(List<Long> teamMates) {
        this.teamMates = teamMates;
    }
}
