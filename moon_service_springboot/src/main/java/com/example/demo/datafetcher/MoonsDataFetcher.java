package com.example.demo.datafetcher;


import com.example.demo.converter.MoonConverter;
import com.example.demo.dto.MoonDto;
import com.example.demo.entities.Moon;
import com.example.demo.services.MoonService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MoonsDataFetcher implements DataFetcher<List<MoonDto>> {

    @Autowired
    private MoonConverter moonConverter;
    @Autowired
    private MoonService moonService;

    @Override
    public List<MoonDto> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        List<Moon> moons = moonService.getAllMoons();
        return moons.stream().map(u -> moonConverter.apply(u)).collect(Collectors.toList());
    }
}
