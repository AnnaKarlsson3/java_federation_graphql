package com.example.demo.services;


import com.example.demo.dto.MoonDto;
import com.example.demo.dto.PlanetDto;
import com.example.demo.entities.Moon;
import com.example.demo.repositories.MoonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoonService {

    @Autowired
    MoonRepository moonRepository;

    public List<Moon> getAllMoons(){
        return (List<Moon>) moonRepository.findAll();
    }

    public Moon getMoonById(int id){
        return (Moon) moonRepository.findById(id).orElse(null);
    }

    public List<Moon> getPlanetsById (int planetId){
        return moonRepository.findByPlanet(planetId);
    }

    public PlanetDto getPlanetId (MoonDto moon){
        return new PlanetDto(moon.getPlanet());
    }

    public Moon create(String name, int size, int planetId){
        Moon moon = new Moon(name, size, planetId);
        moonRepository.save(moon);
        return moon;
    }
}
