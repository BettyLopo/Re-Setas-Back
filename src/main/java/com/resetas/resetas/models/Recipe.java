package com.resetas.resetas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "recipes")
public class Recipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String image;
    private String ingredients;
    private String tools;
    private String steps;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Timestamp duration;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp date;
    private boolean faved;
    private int id_user;
    private int id_category;


    public Recipe() {
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getTools() {
        return this.tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    public String getSteps() {
        return this.steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public Timestamp getDuration() {
        return this.duration;
    }

    public void setDuration(Timestamp duration) {
        this.duration = duration;
    }

    public Timestamp getDate() {
        return this.date;
    }

    public void setCreation(Timestamp date) {
        this.date = date;
    }

    public boolean isFaved() {
        return this.faved;
    }

    public boolean getFaved() {
        return this.faved;
    }

    public void setLike(boolean faved) {
        this.faved = faved;
    }

    public int getId_user() {
        return this.id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_category() {
        return this.id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }
    


}
