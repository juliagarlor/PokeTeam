package com.pokemon.pokemon.controller.impl;

import com.pokemon.pokemon.controller.interfaces.*;
import com.pokemon.pokemon.service.interfaces.*;
import com.pokemon.pokemon.utils.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.PATCH})
public class TrainerController implements ITrainerController {

    @Autowired
    private ITrainerService trainerService;

//    Get all trainers' name and id
    @GetMapping("/trainer/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Object> getNamesAndIds(){
        return trainerService.getNamesAndIds();
    }

//    Get all trainers
    @GetMapping("/trainers/details")
    @ResponseStatus(HttpStatus.OK)
    public List<TrainerDTO> getTrainers(){
        return trainerService.getTrainers();
    }

//    New trainer
    @PostMapping("/new/trainer")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainerDTO createTrainer(@RequestBody @Valid TrainerDTO trainerDTO){
        return trainerService.createTrainer(trainerDTO);
    }

//    Delete trainer
    @DeleteMapping("/remove/trainer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeTrainer(@PathVariable Long id){
        trainerService.removeTrainer(id);
    }
}
