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

    public TeamDTO getTeam(String trainerId) {
        Long numTrainerId = checkNumeric(trainerId);
        Optional<Team> team = teamRepository.findByTrainerIdId(numTrainerId);
        if (team.isPresent()){
            return new TeamDTO(team.get());
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team of trainer with id " + trainerId + " has not " +
                    "been found");
        }
    }

    public TeamDTO addTeamMate(String teamId, String pokedexId) {
        Long numTeamId = checkNumeric(teamId);
        Long numPokedexId = checkNumeric(pokedexId);
        Team searchedTeam = checkId(numTeamId);
        List<Pokemon> teamMates = searchedTeam.getTeamMates();

        if (teamMates.size() > 5){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The max size of a team is 6 members");
        }

        Pokemon newTeamMate = new Pokemon(numPokedexId, searchedTeam);
        pokemonRepository.save(newTeamMate);
        teamMates.add(newTeamMate);
        searchedTeam.setTeamMates(teamMates);

        return new TeamDTO(teamRepository.save(searchedTeam));
    }

    public TeamDTO removeTeamMate(String teamId, String pokemonId) {
        Long numTeamId = checkNumeric(teamId);
        Long numPokemonId = checkNumeric(pokemonId);

        Team searchedTeam = checkId(numTeamId);
        List<Pokemon> teamMates = searchedTeam.getTeamMates();

        Optional<Pokemon> searchedPokemon = pokemonRepository.findById(numPokemonId);
        if (searchedPokemon.isPresent() && searchedPokemon.get().getTeam().getId().equals(numTeamId)){
            teamMates.remove(searchedPokemon.get());
            searchedTeam.setTeamMates(teamMates);
            pokemonRepository.delete(searchedPokemon.get());
            return new TeamDTO(teamRepository.save(searchedTeam));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The pokemon with id " + pokemonId + "does not exist" +
                    " or is not part of this team");
        }
    }

    public Long checkNumeric(String id){
        try{
            return Long.parseLong(id);
        }catch (NumberFormatException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IDs must be numeric values");
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
