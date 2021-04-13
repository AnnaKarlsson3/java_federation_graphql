package com.example.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "moon")
public class Moon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int size;
    private int planet;

    public Moon(){}

    public Moon(int id, String name, int size, int planet) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.planet = planet;
    }

    public Moon(String name, int size, int planet) {
        this.name = name;
        this.size = size;
        this.planet = planet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPlanet() {
        return planet;
    }

    public void setPlanet(int planetId) {
        this.planet = planet;
    }
}
