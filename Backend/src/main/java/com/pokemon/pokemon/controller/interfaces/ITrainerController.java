package com.pokemon.pokemon.controller.interfaces;

import com.pokemon.pokemon.utils.dtos.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

public interface ITrainerController {
    List<Object> getNamesAndIds();
    List<TrainerDTO> getTrainers();
    TrainerDTO createTrainer(TrainerDTO trainerDTO);
    void removeTrainer(Long id);
}
