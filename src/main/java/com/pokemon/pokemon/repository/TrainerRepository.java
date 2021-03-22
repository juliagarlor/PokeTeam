package com.pokemon.pokemon.repository;

import com.pokemon.pokemon.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    @Query("SELECT id, name FROM Trainer ORDER BY id")
    List<Object> getTrainersIdAndNames();
}
