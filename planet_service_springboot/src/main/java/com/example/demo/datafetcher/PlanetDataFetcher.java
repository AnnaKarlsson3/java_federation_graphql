package com.example.demo.datafetcher;


import com.example.demo.converter.PlanetConverter;
import com.example.demo.dto.PlanetDto;
import com.example.demo.entities.Planet;
import com.example.demo.services.PlanetService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PlanetDataFetcher implements DataFetcher<PlanetDto> {

    @Autowired
    private PlanetConverter planetConverter;

    @Autowired
    private PlanetService planetService;

    @Override
    public PlanetDto get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        String id = dataFetchingEnvironment.getArgument("id");
        Planet planet = planetService.getPlanetById(Integer.parseInt(id));
        return planetConverter.apply(planet);
    }
}
