package com.pokemon.pokemon.repository;

import com.pokemon.pokemon.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByTrainerIdId(Long id);
}
