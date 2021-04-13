package com.example.demo.repositories;


import com.example.demo.entities.Moon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoonRepository extends CrudRepository<Moon, Integer> {

    List<Moon> findByPlanet(int planet);
}
