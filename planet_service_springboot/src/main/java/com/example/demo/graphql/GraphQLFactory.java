package com.example.demo.graphql;

import com.example.demo.converter.PlanetConverter;
import com.example.demo.datafetcher.*;
import com.example.demo.dto.PlanetDto;
import com.example.demo.entities.Planet;
import com.example.demo.services.PlanetService;
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

@Component
public class GraphQLFactory {

    @Autowired
    private PlanetDataFetcher planetDataFetcher;
    @Autowired
    private PlanetsDataFetcher planetsDataFetcher;
    @Autowired
    private CreatePlanetDataFetcher createPlanetDataFetcher;
    @Autowired
    PlanetService planetService;
    @Autowired
    PlanetConverter planetConverter;

    @Value("classpath:/schema.graphqls")
     private Resource resource;

    @Bean
    public GraphQLSchema graphQL() throws IOException {
       InputStream inputStream = resource.getInputStream();

        //This is only needed if another service wants to implement Planet in its schema
        List<FederatedEntityResolver<?, ?>> entityResolvers = List.of(
                new FederatedEntityResolver<Integer, PlanetDto>("Planet", id ->{
                    Planet planet = planetService.getPlanetById(id);
                    PlanetDto planetDto = planetConverter.apply(planet);
                    return planetDto;
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
                                .dataFetcher("planet", planetDataFetcher)
                                .dataFetcher("planets", planetsDataFetcher)
                )
                .type("Mutation", builder ->
                        builder.dataFetcher("createPlanet", createPlanetDataFetcher)
                )
                .build();

    }

}

