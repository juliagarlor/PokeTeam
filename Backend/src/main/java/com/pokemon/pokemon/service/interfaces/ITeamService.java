package com.pokemon.pokemon.service.interfaces;

import com.pokemon.pokemon.utils.dtos.*;

public interface ITeamService {
    TeamDTO getTeam(Long trainerId);
    TeamDTO addTeamMate(Long teamId, Long pokedexId);
    TeamDTO removeTeamMate(Long teamId, Long pokemonId);
}
