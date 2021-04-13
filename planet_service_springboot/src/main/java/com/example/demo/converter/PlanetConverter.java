package com.example.demo.converter;


import com.example.demo.dto.PlanetDto;
import com.example.demo.entities.Planet;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PlanetConverter implements Function<Planet, PlanetDto> {

    @Override
    public PlanetDto apply(Planet planet) {
        return new PlanetDto(planet.getId(), planet.getName(), planet.getSize());
    }
}
