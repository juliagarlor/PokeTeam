package com.pokemon.pokemon.repository;

import com.pokemon.pokemon.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
