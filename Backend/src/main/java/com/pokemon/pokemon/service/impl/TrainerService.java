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
public class TrainerService implements ITrainerService {
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PokemonRepository pokemonRepository;

    public List<Object> getNamesAndIds() {
        return trainerRepository.getTrainersIdAndNames();
    }

    public List<TrainerDTO> getTrainers() {
        List<Trainer> trainers = trainerRepository.findAll();
        List<TrainerDTO> output = new ArrayList<>();
        for(Trainer trainer : trainers){
            output.add(new TrainerDTO(trainer));
        }
        return output;
    }

//    Create new trainer and its team
    public TrainerDTO createTrainer(TrainerDTO trainerDTO) {
        Trainer newTrainer = new Trainer(trainerDTO);
        trainerRepository.save(newTrainer);
        Team newTeam = new Team(newTrainer);
        teamRepository.save(newTeam);
        return new TrainerDTO(newTrainer);
    }

//    Remove trainer and its team
    public void removeTrainer(Long id) {
        Optional<Trainer> trainerToRemove = trainerRepository.findById(id);
        if (trainerToRemove.isPresent()){
//            Look for the team to remove
            Team teamToRemove = teamRepository.findByTrainerIdId(id).get();
            List<Pokemon> pokemonsToRemove = teamToRemove.getTeamMates();
//            Clean the team mates list
            teamToRemove.setTeamMates(new ArrayList<>());
//            Remove pokemons from this team
            pokemonRepository.deleteAll(pokemonsToRemove);
//            Remove the team
            teamRepository.delete(teamToRemove);
//            Remove the trainer
            trainerRepository.delete(trainerToRemove.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer with id " + id + " has not been found");
        }
    }
}
