package com.example.demo.datafetcher;


import com.example.demo.converter.MoonConverter;
import com.example.demo.dto.MoonDto;
import com.example.demo.services.MoonService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CreateMoonDataFetcher implements DataFetcher<MoonDto> {

    @Autowired
    private MoonService moonService;
    @Autowired
    private MoonConverter moonConverter;

    @Override
    public MoonDto get(DataFetchingEnvironment environment) throws Exception {
        String name = environment.getArgument("name");
        int size = environment.getArgument("size");
        int planetId = environment.getArgument("planetId");

        var newMoon = moonService.create(name, size, planetId);

        return moonConverter.apply(newMoon);
    }
}
