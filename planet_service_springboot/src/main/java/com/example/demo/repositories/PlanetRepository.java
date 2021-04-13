package com.example.demo.repositories;


import com.example.demo.entities.Planet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends CrudRepository<Planet, Integer> {
}
