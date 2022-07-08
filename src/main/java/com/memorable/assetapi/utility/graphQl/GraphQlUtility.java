package com.memorable.assetapi.utility.graphQl;

import com.memorable.assetapi.utility.dataFetcher.AssetByTypeAndScoreTypeDataFetcher;
import com.memorable.assetapi.utility.dataFetcher.AssetDataFetcher;
import com.memorable.assetapi.utility.dataFetcher.CreateAssetDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

import static com.memorable.assetapi.utility.graphQl.EndpointConstants.ASSETS_ENDPOINT;
import static com.memorable.assetapi.utility.graphQl.EndpointConstants.ASSET_ENDPOINT;
import static com.memorable.assetapi.utility.graphQl.EndpointConstants.CREATE_ASSET_ENDPOINT;
import static com.memorable.assetapi.utility.graphQl.EndpointConstants.MUTATION;
import static com.memorable.assetapi.utility.graphQl.EndpointConstants.QUERY;
import static graphql.GraphQL.newGraphQL;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@Component
public class GraphQlUtility {

    @Value("classpath:graphql/asset.graphqls")
    private Resource schemaResource;
    private AssetByTypeAndScoreTypeDataFetcher assetByTypeAndScoreTypeDataFetcher;
    private AssetDataFetcher assetDataFetcher;
    private CreateAssetDataFetcher createAssetDataFetcher;

    @Autowired
    GraphQlUtility(AssetByTypeAndScoreTypeDataFetcher assetByTypeAndScoreTypeDataFetcher,
                   AssetDataFetcher assetDataFetcher,
                   CreateAssetDataFetcher createAssetDataFetcher) throws IOException {
        this.assetByTypeAndScoreTypeDataFetcher = assetByTypeAndScoreTypeDataFetcher;
        this.assetDataFetcher = assetDataFetcher;
        this.createAssetDataFetcher = createAssetDataFetcher;
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
                .type(QUERY, typeWiring -> typeWiring
                        .dataFetcher(ASSETS_ENDPOINT, assetByTypeAndScoreTypeDataFetcher)
                        .dataFetcher(ASSET_ENDPOINT, assetDataFetcher))
                .type(MUTATION, typeWiring -> typeWiring
                        .dataFetcher(CREATE_ASSET_ENDPOINT, createAssetDataFetcher))
                .build();
    }

}
