package com.pokemon.pokemon.controller.interfaces;

import com.pokemon.pokemon.utils.dtos.*;
import org.springframework.web.bind.annotation.*;

public interface ITeamController {
    TeamDTO getTeam(String trainerId);
    TeamDTO addTeamMate(String teamId, String pokedexId);
    TeamDTO removeTeamMate(String teamId, String pokemonId);
}
