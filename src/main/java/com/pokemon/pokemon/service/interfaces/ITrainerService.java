package com.pokemon.pokemon.service.interfaces;

import com.pokemon.pokemon.utils.dtos.*;

import java.util.*;

public interface ITrainerService {
    List<Object> getNamesAndIds();
    List<TrainerDTO> getTrainers();
    TrainerDTO createTrainer(TrainerDTO trainerDTO);
    void removeTrainer(Long id);
}
