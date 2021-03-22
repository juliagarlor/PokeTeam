package com.pokemon.pokemon.model;

import com.pokemon.pokemon.utils.dtos.*;

import javax.persistence.*;

@Entity
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String hobby;
    private String photo;

//    Constructors

    public Trainer() {
    }

    public Trainer(String name, int age, String hobby, String photo) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
        this.photo = photo;
    }

    public Trainer(TrainerDTO trainerDTO) {
        this(trainerDTO.getName(), trainerDTO.getAge(), trainerDTO.getHobby(), trainerDTO.getPhoto());
    }

    //    Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
