package com.pokemon.pokemon.controller.impl;

import com.pokemon.pokemon.controller.interfaces.*;
import com.pokemon.pokemon.service.interfaces.*;
import com.pokemon.pokemon.utils.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.PATCH})
public class TeamController implements ITeamController {

    @Autowired
    private ITeamService teamService;

//    Get team by trainerId
    @GetMapping("/team/{trainerId}")
    @ResponseStatus(HttpStatus.OK)
    public TeamDTO getTeam(@PathVariable Long trainerId){
        return teamService.getTeam(trainerId);
    }

//    Add a new team mate
    @PutMapping("/new/team-mate/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    public TeamDTO addTeamMate(@PathVariable Long teamId, @RequestBody Long pokedexId){
        return teamService.addTeamMate(teamId, pokedexId);
    }

//    Remove a team mate
    @PutMapping("/remove/team-mate/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    public TeamDTO removeTeamMate(@PathVariable Long teamId, @RequestBody Long pokemonId){
        return teamService.removeTeamMate(teamId, pokemonId);
    }
}
