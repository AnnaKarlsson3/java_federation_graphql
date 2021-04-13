package com.example.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "planet")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int size;

    public Planet(){}

    public Planet(int id, String name, int size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public Planet(String name, int size) {
        this.name = name;
        this.size = size;
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
}
