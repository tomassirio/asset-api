package com.memorable.assetapi.controller;

import com.memorable.assetapi.model.Asset;
import com.memorable.assetapi.service.AssetService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

import static com.memorable.assetapi.utils.TestUtils.createAsset;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@GraphQLTest
public class AssetControllerTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    private AssetService assetServiceMock;

    static Asset asset;


    @BeforeAll
    static void setUp() {
        asset = createAsset();
    }

    @Test
    public void createUser() throws IOException {
        doReturn(asset).when(assetServiceMock).createAsset(any());
        GraphQLResponse response = graphQLTestTemplate.postForResource("/query");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.createUser.id")).isNotNull();
        assertThat(response.get("$.data.createUser.username")).isEqualTo(TEST_USERNAME);

    }
}
