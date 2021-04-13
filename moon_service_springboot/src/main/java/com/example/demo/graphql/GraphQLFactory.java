package com.example.demo.graphql;

import com.example.demo.converter.MoonConverter;
import com.example.demo.datafetcher.*;
import com.example.demo.dto.MoonDto;
import com.example.demo.dto.PlanetDto;
import com.example.demo.entities.Moon;
import com.example.demo.services.MoonService;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import io.gqljf.federation.FederatedEntityResolver;
import io.gqljf.federation.FederatedSchemaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GraphQLFactory {

    @Autowired
    private MoonDataFetcher moonDataFetcher;
    @Autowired
    private MoonsDataFetcher moonsDataFetcher;
    @Autowired
    private CreateMoonDataFetcher createPlanetDataFetcher;
    @Autowired
    private PlanetDataFetcher planetDataFetcher;
    @Autowired
    private MoonService moonService;
    @Autowired
    private MoonConverter moonConverter;

    @Value("classpath:/schema.graphqls")
     private Resource resource;

    @Bean
    public GraphQLSchema graphQL() throws IOException {
       InputStream inputStream = resource.getInputStream();

        //This is only needed if this service wants to extends Planet in the schema
        List<FederatedEntityResolver<?, ?>> entityResolvers = List.of(
                new FederatedEntityResolver<Integer, PlanetDto>("Planet", id ->{
                   List<Moon> moons = moonService.getPlanetsById(id);
                   List<MoonDto> moonDtos = moons.stream().map(u -> moonConverter.apply(u)).collect(Collectors.toList());
                    return new PlanetDto(id, moonDtos);
                }) {}
        );

        GraphQLSchema transformedGraphQLSchema = new FederatedSchemaBuilder()
                .schemaInputStream(inputStream)
                .runtimeWiring(createRuntimeWiring())
                .excludeSubscriptionsFromApolloSdl(true)
                .federatedEntitiesResolvers(entityResolvers)
                .build();

        return GraphQLSchema.newSchema(transformedGraphQLSchema)
                .build();
    }


    private RuntimeWiring createRuntimeWiring(){
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", builder ->
                        builder
                                .dataFetcher("moon", moonDataFetcher)
                                .dataFetcher("moons", moonsDataFetcher)
                )
                .type("Mutation", builder ->
                        builder.dataFetcher("createMoon", createPlanetDataFetcher)
                )
                //this is needed if one of Moon´s field (planet) is of type Entity (Planet) -> make a new PlanetDto, set planet id from moonDto.getPlanetId
                .type("Moon", builder ->
                        builder
                                .dataFetcher("planet", planetDataFetcher)
                )
                .build();

    }

}

