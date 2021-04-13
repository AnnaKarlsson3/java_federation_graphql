package com.example.demo.services;


import com.example.demo.entities.Planet;
import com.example.demo.repositories.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetService {

    @Autowired
    PlanetRepository planetRepository;

    public List<Planet> getAllPlanets(){
        return (List<Planet>) planetRepository.findAll();
    }

    public Planet getPlanetById(int id){
        return (Planet) planetRepository.findById(id).orElse(null);
    }

    public Planet create(String name, int size){
        Planet planet = new Planet(name, size);
        planetRepository.save(planet);
        return planet;
    }
}
