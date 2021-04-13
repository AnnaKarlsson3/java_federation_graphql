package com.example.demo.datafetcher;

import com.example.demo.dto.MoonDto;
import com.example.demo.dto.PlanetDto;
import com.example.demo.entities.Moon;
import com.example.demo.services.MoonService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlanetDataFetcher implements DataFetcher<PlanetDto> {

    @Autowired
    private MoonService moonService;

    @Override
    public PlanetDto get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        MoonDto moon = dataFetchingEnvironment.getSource();
        return moonService.getPlanetId(moon);
    }
}
