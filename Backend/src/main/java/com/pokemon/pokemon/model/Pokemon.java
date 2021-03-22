package com.pokemon.pokemon.model;

import javax.persistence.*;

@Entity
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pokedexId;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

//    Constructors

    public Pokemon() {
    }

    public Pokemon(Long pokedexId, Team team) {
        this.pokedexId = pokedexId;
        this.team = team;
    }

//    Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPokedexId() {
        return pokedexId;
    }

    public void setPokedexId(Long pokedexId) {
        this.pokedexId = pokedexId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
