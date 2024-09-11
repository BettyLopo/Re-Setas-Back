package com.resetas.resetas.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "recipes")
public class Recipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    @NotNull(message = "La receta debe tener nombre")
    private String title;

    @Column(nullable = false)
    @NotNull(message = "La receta debe tener una imagen")
    private String image;

    @Column(nullable = false)
    @NotNull(message = "La receta debe tener al menos un ingrediente")
    private String ingredients;

    @Column(nullable = false)
    @NotNull(message = "La receta debe tener al menos una herramienta")
    private String tools;

    @Column(nullable = false)
    @NotNull(message = "La receta debe tener una explicación")
    private String steps;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Timestamp duration;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp date;

    @Column(nullable = false)
    private boolean faved;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    @NotNull(message = "La receta debe pertenecer a una categoría")
    @JsonIgnore
    private Category category;


    public Recipe() {}


    @PrePersist
    protected void onCreate() {
        this.date = Timestamp.from(Instant.now()); 
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

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public boolean isFaved() {
        return this.faved;
    }

    public boolean getFaved() {
        return this.faved;
    }

    public void setFaved(boolean faved) {
        this.faved = faved;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }



}
