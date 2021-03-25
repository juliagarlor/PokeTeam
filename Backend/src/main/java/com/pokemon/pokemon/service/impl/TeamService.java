package com.pokemon.pokemon.service.impl;

import com.pokemon.pokemon.model.*;
import com.pokemon.pokemon.repository.*;
import com.pokemon.pokemon.service.interfaces.*;
import com.pokemon.pokemon.utils.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.util.*;

@Service
public class TeamService implements ITeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PokemonRepository pokemonRepository;

    public TeamDTO getTeam(Long trainerId) {
        Optional<Team> team = teamRepository.findByTrainerIdId(trainerId);
        if (team.isPresent()){
            return new TeamDTO(team.get());
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team of trainer with id " + trainerId + " has not " +
                    "been found");
        }
    }

    public TeamDTO addTeamMate(Long teamId, Long pokedexId) {
        Team searchedTeam = checkId(teamId);
        List<Pokemon> teamMates = searchedTeam.getTeamMates();

        if (teamMates.size() > 5){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The max size of a team is 6 members");
        }

        Pokemon newTeamMate = new Pokemon(pokedexId, searchedTeam);
        pokemonRepository.save(newTeamMate);
        teamMates.add(newTeamMate);
        searchedTeam.setTeamMates(teamMates);

        return new TeamDTO(teamRepository.save(searchedTeam));
    }

    public TeamDTO removeTeamMate(Long teamId, Long pokemonId) {
        Team searchedTeam = checkId(teamId);
        List<Pokemon> teamMates = searchedTeam.getTeamMates();

        Optional<Pokemon> searchedPokemon = pokemonRepository.findById(pokemonId);
        if (searchedPokemon.isPresent() && searchedPokemon.get().getTeam().getId().equals(teamId)){
            teamMates.remove(searchedPokemon.get());
            searchedTeam.setTeamMates(teamMates);
            pokemonRepository.delete(searchedPokemon.get());
            return new TeamDTO(teamRepository.save(searchedTeam));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The pokemon with id " + pokemonId + "does not exist" +
                    " or is not part of this team");
        }
    }

    public Team checkId(Long teamId){
        Optional<Team> output = teamRepository.findById(teamId);
        if (output.isPresent()){
            return output.get();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team with id " + teamId + " has not been found");
        }
    }
}
