# SWAPI Labs

Some sample applications based on the Star Wars API (https://swapi.co/).


## Samples

### `swapi-labs-graphql`

Sample application providing a [GraphQL][] web service.

Run the sample with:

    ./gradlew clean :swapi-labs-graphql:bootRun

The GraphiQL interactive interface is available at `http://localhost:8080/graphiql`.

The sample uses [Spring Boot][], [Spring Data JPA][], [GraphQL Java][] and the [GraphQL Spring Boot][] Starters.


### `swapi-labs-jsonapi`

Sample application providing a RESTful web service following the [JSON API][] specification. 

Run the sample with:

    ./gradlew clean :swapi-labs-jsonapi:bootRun

The sample uses [Spring Boot][], [Spring Data JPA][] and the [JSON API Converter][] library. 

Note: development not yet started. 


## Refreshing SWAPI data

The  SWAPI data is stored in `swapi-labs-data/src/main/resources/data.sql`.

To refresh the `data.sql` simply run:

    ./gradlew refreshSwapiData  


## License

This software is released under version 2.0 of the [Apache License][].


[Apache License]: http://www.apache.org/licenses/LICENSE-2.0
[GraphQL]: http://graphql.org/
[GraphQL Java]: https://github.com/graphql-java/graphql-java
[GraphQL Spring Boot]: https://github.com/graphql-java/graphql-spring-boot
[JSON API]: http://jsonapi.org/
[JSON API Converter]: https://github.com/jasminb/jsonapi-converter
[Spring Boot]: https://github.com/spring-projects/spring-boot
[Spring Data JPA]: https://github.com/spring-projects/spring-data-jpa
