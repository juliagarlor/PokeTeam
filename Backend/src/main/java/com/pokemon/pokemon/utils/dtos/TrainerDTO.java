package com.pokemon.pokemon.utils.dtos;

import com.pokemon.pokemon.model.*;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;
import javax.validation.constraints.NotEmpty;

public class TrainerDTO {
    private Long id;
    @NotEmpty(message = "Please, choose a name")
    @Length(min = 2, max = 20, message = "Please, choose a name with a length between 2 and 20 characters")
    private String name;
    @Min(10)
    private int age;
    private String hobby;
    @NotEmpty(message = "Please, choose your favourite photo")
    private String photo;

//    Constructors

    public TrainerDTO(String name, int age, String hobby, String photo) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
        this.photo = photo;
    }

    public TrainerDTO(Trainer trainer) {
        this(trainer.getName(), trainer.getAge(), trainer.getHobby(), trainer.getPhoto());
        setId(trainer.getId());
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
