package com.pokemon.pokemon.utils.dtos;

import com.pokemon.pokemon.model.*;

public class PokemonDTO {
    private Long id;
    private Long pokedexId;

//    Constructors

    public PokemonDTO(Long pokedexId) {
        this.pokedexId = pokedexId;
    }

    public PokemonDTO(Pokemon pokemon) {
        this(pokemon.getPokedexId());
        setId(pokemon.getId());
    }

//    Getters and setters

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
}
