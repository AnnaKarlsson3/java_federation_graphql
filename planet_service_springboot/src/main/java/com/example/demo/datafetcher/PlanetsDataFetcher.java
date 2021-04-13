package com.example.demo.datafetcher;


import com.example.demo.converter.PlanetConverter;
import com.example.demo.dto.PlanetDto;
import com.example.demo.entities.Planet;
import com.example.demo.services.PlanetService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlanetsDataFetcher implements DataFetcher<List<PlanetDto>> {

    @Autowired
    private PlanetConverter userConverter;
    @Autowired
    private PlanetService userService;


    @Override
    public List<PlanetDto> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        List<Planet> allUsers = userService.getAllPlanets();
        return allUsers.stream().map(u -> userConverter.apply(u)).collect(Collectors.toList());
    }
}
