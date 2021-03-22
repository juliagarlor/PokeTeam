package com.pokemon.pokemon.controller.interfaces;

import com.pokemon.pokemon.utils.dtos.*;
import org.springframework.web.bind.annotation.*;

public interface ITeamController {
    TeamDTO getTeam(Long trainerId);
    TeamDTO addTeamMate(Long teamId, Long pokedexId);
    TeamDTO removeTeamMate(Long teamId, Long pokemonId);
}
