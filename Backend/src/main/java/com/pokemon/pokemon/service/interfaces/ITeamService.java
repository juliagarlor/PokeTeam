package com.pokemon.pokemon.service.interfaces;

import com.pokemon.pokemon.utils.dtos.*;

public interface ITeamService {
    TeamDTO getTeam(String trainerId);
    TeamDTO addTeamMate(String teamId, String pokedexId);
    TeamDTO removeTeamMate(String teamId, String pokemonId);
}
