# Memorable Asset API

This project was developed as a test project for the company Memorable

## Technologies

    - Java 11
    - Spring Boot
    - GraphQl java tools
    - Mongo Db

## How To Run?
    
The project can be compiled with the following commands 
    
```shell
mvn clean install
mvn spring-boot:run
```

Be sure to have a mongo instance up so that the database can be created

## How To Use?

There's 3 GraphQl endpoints controlled by a single POST endpoint under port 8000.

In order to reach the endpoints, you can send a query message to ```localhost:8000/query```

### Endpoints

#### Queries
* asset(String id)
* assets(String assetType, Integer scoreType)

#### Mutations
* createAsset(AssetRequest assetRequest)

### Examples

- createAsset

```shell
curl --location --request POST 'localhost:8000/query' \
--header 'Content-Type: text/plain' \
--data-raw 'mutation { 
    createAsset(request:{name: "assasa", assetType: "image", scores:[1,2,3]})
}{
    id
    name
    assetType
    scores
}'
```

- asset

```shell
curl --location --request POST 'localhost:8000/query' \
--header 'Content-Type: text/plain' \
--data-raw '{ 
    asset(id:"0000325e-b7ca-4f6d-8b76-08464d993610")
}{
    id
    name
    assetType
    scores
}'
```

- assets

```shell
curl --location --request POST 'localhost:8000/query' \
--header 'Content-Type: text/plain' \
--data-raw '{
assets(assetType: "image", scoreType: 1)
}{
id
name
assetType
scores
}'`
```