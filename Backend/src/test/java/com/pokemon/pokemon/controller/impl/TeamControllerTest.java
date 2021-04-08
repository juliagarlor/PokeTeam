package com.pokemon.pokemon.controller.impl;

import com.fasterxml.jackson.databind.*;
import com.pokemon.pokemon.model.*;
import com.pokemon.pokemon.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TeamControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PokemonRepository pokemonRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

//        Let's first create a trainer
        Trainer jessy = new Trainer("Jessy", 20, "Prepare for trouble...", "jessy.jpg");
        trainerRepository.save(jessy);
//        Let's create Jessy's team
        Team jTeam = new Team(jessy);
        teamRepository.save(jTeam);
//        And add ekans to the team
        Pokemon ekans = new Pokemon(23L, jTeam);
        pokemonRepository.save(ekans);
    }

    @AfterEach
    void tearDown() {
        pokemonRepository.deleteAll();
        teamRepository.deleteAll();
        trainerRepository.deleteAll();
    }

    @Test
    void getTeam_existingId_TeamDTO() throws Exception {
        Team test = teamRepository.findAll().get(0);
        MvcResult result = mockMvc.perform(get("/team/" + test.getId())).andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("\"pokedexId\":23"));
    }

    @Test
    void getTeam_nonExistingId_notFoundException() throws Exception {
        Team test = teamRepository.findAll().get(0);
        MvcResult result = mockMvc.perform(get("/team/" + (test.getId() + 1))).andExpect(status().isNotFound())
                .andReturn();
        assertTrue(result.getResolvedException().toString().contains("has not been found"));
    }

    @Test
    void getTeam_nonNumericSymbols_badRequestException() throws Exception {
        MvcResult result = mockMvc.perform(get("/team/a")).andExpect(status().isBadRequest())
                .andReturn();
        assertTrue(result.getResolvedException().toString().contains("IDs must be numeric values"));
    }

    @Test
    void addTeamMate_validTeamIdValidPokedexIdAndTeamNotFull_TeamDTO() throws Exception {
        Team test = teamRepository.findAll().get(0);
        assertEquals(1, test.getTeamMates().size());
//        let's add wobbuffet
        String body = objectMapper.writeValueAsString(202L);
        MvcResult result = mockMvc.perform(put("/new/team-mate/" + test.getId()).content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("\"pokedexId\":202"));
        assertEquals(2, teamRepository.findByTrainerIdId(test.getId()).get().getTeamMates().size());
    }

    @Test
    void addTeamMate_invalidTeamIdValidPokedexIdAndTeamNotFull_TeamDTO() throws Exception {
        Team test = teamRepository.findAll().get(0);
//        let's add wobbuffet
        String body = objectMapper.writeValueAsString(202L);
        MvcResult result = mockMvc.perform(put("/new/team-mate/" + (test.getId() + 1)).content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("has not been found"));
        assertEquals(1, test.getTeamMates().size());
    }

    @Test
    void addTeamMate_validTeamNonNumericPokedexIdAndTeamNotFull_TeamDTO() throws Exception {
        Team test = teamRepository.findAll().get(0);
//        let's add wobbuffet
        String body = objectMapper.writeValueAsString("wobbuffet");
        MvcResult result = mockMvc.perform(put("/new/team-mate/" + test.getId()).content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("IDs must be numeric values"));
        assertEquals(1, test.getTeamMates().size());
    }

    @Test
    void addTeamMate_validTeamIdAndTeamFull_TeamDTO() throws Exception {
        Team test = teamRepository.findAll().get(0);
//        Let's add 5 magikarps
        List<Pokemon> jessyPokemons = test.getTeamMates();
        Pokemon fish1 = new Pokemon(129L, test);
        Pokemon fish2 = new Pokemon(129L, test);
        Pokemon fish3 = new Pokemon(129L, test);
        Pokemon fish4 = new Pokemon(129L, test);
        Pokemon fish5 = new Pokemon(129L, test);
        pokemonRepository.saveAll(List.of(fish1, fish2, fish3, fish4, fish5));
        jessyPokemons.add(fish1);
        jessyPokemons.add(fish2);
        jessyPokemons.add(fish3);
        jessyPokemons.add(fish4);
        jessyPokemons.add(fish5);
        test.setTeamMates(jessyPokemons);
        teamRepository.save(test);
//        and wobbuffet
        String body = objectMapper.writeValueAsString(202L);
        MvcResult result = mockMvc.perform(put("/new/team-mate/" + test.getId()).content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("The max size of a team is 6 members"));
        assertEquals(6, test.getTeamMates().size());
    }

    @Test
    void removeTeamMate_validTeamIdAndPokemonFromThisTeam_TeamDTO() throws Exception {
        Team test = teamRepository.findAll().get(0);
        assertEquals(1, test.getTeamMates().size());
//        let's remove ekans
        Long ekansId = pokemonRepository.findAll().get(0).getId();
        String body = objectMapper.writeValueAsString(ekansId);
        MvcResult result = mockMvc.perform(put("/remove/team-mate/" + test.getId()).content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("\"teamMates\":[]"));
        assertEquals(0, teamRepository.findById(test.getId()).get().getTeamMates().size());
    }

    @Test
    void removeTeamMate_invalidTeamIdAndPokemonFromThisTeam_TeamDTO() throws Exception {
        Team test = teamRepository.findAll().get(0);
        assertEquals(1, test.getTeamMates().size());
//        let's remove ekans
        Long ekansId = pokemonRepository.findAll().get(0).getId();
        String body = objectMapper.writeValueAsString(ekansId);
        MvcResult result = mockMvc.perform(put("/remove/team-mate/" + (test.getId() + 1)).content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();

        assertTrue(result.getResolvedException().toString().contains("has not been found"));
        assertEquals(1, test.getTeamMates().size());
    }

    @Test
    void removeTeamMate_validTeamIdAndPokemonNotInTeam_TeamDTO() throws Exception {
        Team test = teamRepository.findAll().get(0);
        assertEquals(1, test.getTeamMates().size());
//        let's remove ekans
        Long ekansId = pokemonRepository.findAll().get(0).getId();
        String body = objectMapper.writeValueAsString(ekansId + 1);
        MvcResult result = mockMvc.perform(put("/remove/team-mate/" + test.getId()).content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();

        assertTrue(result.getResolvedException().toString().contains("is not part of this team"));
        assertEquals(1, test.getTeamMates().size());
    }
}