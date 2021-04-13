package com.example.demo.datafetcher;


import com.example.demo.converter.PlanetConverter;
import com.example.demo.dto.PlanetDto;
import com.example.demo.services.PlanetService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CreatePlanetDataFetcher implements DataFetcher<PlanetDto> {

    @Autowired
    PlanetService planetService;
    @Autowired
    PlanetConverter planetConverter;

    @Override
    public PlanetDto get(DataFetchingEnvironment environment) throws Exception {
        String name = environment.getArgument("name");
        int size = environment.getArgument("size");

        var newPlanet = planetService.create(name, size);
        return planetConverter.apply(newPlanet);
    }
}