package com.example.demo.converter;


import com.example.demo.dto.MoonDto;
import com.example.demo.dto.PlanetDto;
import com.example.demo.entities.Moon;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MoonConverter implements Function<Moon, MoonDto> {

    @Override
    public MoonDto apply(Moon moon) {
        return new MoonDto(moon.getId(), moon.getName(), moon.getSize(), moon.getPlanet());
    }
}
