package com.memorable.assetapi.utility.graphql;

import com.memorable.assetapi.utility.dataFetcher.AssetByTypeAndScoreTypeDataFetcher;
import com.memorable.assetapi.utility.dataFetcher.AssetDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

import static graphql.GraphQL.newGraphQL;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@Component
public class GraphQlUtility {

    @Value("classpath:schemas.graphqls")
    private Resource schemaResource;
    private GraphQL graphQL;
    private AssetByTypeAndScoreTypeDataFetcher assetByTypeAndScoreTypeDataFetcher;
    private AssetDataFetcher assetDataFetcher;

    @Autowired
    GraphQlUtility(AssetByTypeAndScoreTypeDataFetcher assetByTypeAndScoreTypeDataFetcher,
                   AssetDataFetcher assetDataFetcher) throws IOException {
        this.assetByTypeAndScoreTypeDataFetcher = assetByTypeAndScoreTypeDataFetcher;
        this.assetDataFetcher = assetDataFetcher;
    }

    @PostConstruct
    public GraphQL createGraphQlObject() throws IOException {
        File schemas = schemaResource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemas);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);

        return newGraphQL(schema).build();
    }

    public RuntimeWiring buildRuntimeWiring() {
        return newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("assets", assetByTypeAndScoreTypeDataFetcher)
                        .dataFetcher("asset", assetDataFetcher))
                .build();
    }

}
