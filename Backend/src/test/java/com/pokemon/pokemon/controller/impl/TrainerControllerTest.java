package com.pokemon.pokemon.controller.impl;

import com.fasterxml.jackson.databind.*;
import com.pokemon.pokemon.model.*;
import com.pokemon.pokemon.repository.*;
import com.pokemon.pokemon.utils.dtos.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TrainerControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private TeamRepository teamRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        Let's create Jessy
        Trainer jessy = new Trainer("Jessy", 20, "Prepare for trouble...", "jessy.jpg");
        trainerRepository.save(jessy);
//        and its team
        Team jTeam = new Team(jessy);
        teamRepository.save(jTeam);
    }

    @AfterEach
    void tearDown() {
        teamRepository.deleteAll();
        trainerRepository.deleteAll();
    }

    @Test
    void getNamesAndIds_void_ListOfNamesAndIds() throws Exception {
        MvcResult result = mockMvc.perform(get("/trainer/list")).andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString().contains("Jessy"));
        assertFalse(result.getResponse().getContentAsString().contains("Prepare for trouble..."));
    }

    @Test
    void getTrainers_void_listOfTrainerDTOs() throws Exception {
        MvcResult result = mockMvc.perform(get("/trainers/details")).andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString().contains("Jessy"));
        assertTrue(result.getResponse().getContentAsString().contains("Prepare for trouble..."));
    }

    @Test
    void createTrainer_validTrainerDTO_TrainerDTO() throws Exception {
        assertEquals(1, trainerRepository.findAll().size());
        TrainerDTO newTrainer = new TrainerDTO("James", 21, "and make it double", "james.jpg");
        String body = objectMapper.writeValueAsString(newTrainer);
        MvcResult result = mockMvc.perform(post("/new/trainer").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("James"));
        assertEquals(2, trainerRepository.findAll().size());
        assertEquals(2, teamRepository.findAll().size());
    }

    @Test
    void createTrainer_invalidTrainerDTO_badRequestException() throws Exception {
        assertEquals(1, trainerRepository.findAll().size());
        TrainerDTO newTrainer = new TrainerDTO("", 21, "and make it double", "james.jpg");
        String body = objectMapper.writeValueAsString(newTrainer);
        MvcResult result = mockMvc.perform(post("/new/trainer").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();

        assertTrue(result.getResolvedException().toString().contains("Please, choose a name"));
        assertEquals(1, trainerRepository.findAll().size());
    }

    @Test
    void removeTrainer_validTrainerId_void() throws Exception {
        assertEquals(1, trainerRepository.findAll().size());
        Long jessyId = trainerRepository.findAll().get(0).getId();
        mockMvc.perform(delete("/remove/trainer/" + jessyId)).andExpect(status().isNoContent());

        assertEquals(0, trainerRepository.findAll().size());
        assertEquals(0, teamRepository.findAll().size());
    }

    @Test
    void removeTrainer_invalidTrainerId_notFoundException() throws Exception {
        assertEquals(1, trainerRepository.findAll().size());
        Long jessyId = trainerRepository.findAll().get(0).getId();
        mockMvc.perform(delete("/remove/trainer/" + (jessyId + 1))).andExpect(status().isNotFound());

        assertEquals(1, trainerRepository.findAll().size());
    }
}