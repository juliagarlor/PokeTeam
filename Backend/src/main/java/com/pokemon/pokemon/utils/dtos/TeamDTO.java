package com.pokemon.pokemon.utils.dtos;

import com.pokemon.pokemon.model.*;

import java.util.*;

public class TeamDTO {
    private Long id;
    private Long trainerId;
    private List<PokemonDTO> teamMates;

//    Constructors

    public TeamDTO(Long trainerId, List<PokemonDTO> teamMates) {
        this.trainerId = trainerId;
        this.teamMates = teamMates;
    }

    public TeamDTO(Team team) {
        this(team.getTrainerId().getId(), team.getTeamMatesDTOs());
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

    public List<PokemonDTO> getTeamMates() {
        return teamMates;
    }

    public void setTeamMates(List<PokemonDTO> teamMates) {
        this.teamMates = teamMates;
    }
}
