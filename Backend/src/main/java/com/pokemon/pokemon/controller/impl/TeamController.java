package com.pokemon.pokemon.controller.impl;

import com.pokemon.pokemon.controller.interfaces.*;
import com.pokemon.pokemon.service.interfaces.*;
import com.pokemon.pokemon.utils.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;
import org.springframework.web.server.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.PATCH})
public class TeamController implements ITeamController {

    @Autowired
    private ITeamService teamService;

//    Get team by trainerId
    @GetMapping("/team/{trainerId}")
    @ResponseStatus(HttpStatus.OK)
    public TeamDTO getTeam(@PathVariable String trainerId){
        return teamService.getTeam(trainerId);
    }

//    Add a new team mate
    @PutMapping("/new/team-mate/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    public TeamDTO addTeamMate(@PathVariable String teamId, @RequestBody String pokedexId){
        return teamService.addTeamMate(teamId, pokedexId);
    }

//    Remove a team mate
    @PutMapping("/remove/team-mate/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    public TeamDTO removeTeamMate(@PathVariable String teamId, @RequestBody String pokemonId){
        return teamService.removeTeamMate(teamId, pokemonId);
    }
}
