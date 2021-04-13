package com.example.demo.datafetcher;


import com.example.demo.converter.MoonConverter;
import com.example.demo.dto.MoonDto;
import com.example.demo.entities.Moon;
import com.example.demo.services.MoonService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MoonDataFetcher implements DataFetcher<MoonDto> {

    @Autowired
    private MoonConverter moonConverter;
    @Autowired
    private MoonService moonService;

    @Override
    public MoonDto get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        String id = dataFetchingEnvironment.getArgument("id");
        Moon moon = moonService.getMoonById(Integer.parseInt(id));
        return moonConverter.apply(moon);
    }
}
