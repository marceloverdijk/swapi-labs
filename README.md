# SWAPI Labs

Some sample applications based on the Star Wars API (https://swapi.co/).


## Samples

### `swapi-labs-graphql`

Sample application providing a [GraphQL](http://graphql.org/) web service.

Run the sample with:

    ./gradlew clean :swapi-labs-graphql:bootRun

The GraphiQL interactive interface is available at `http://localhost:8080/graphiql`.


### `swapi-labs-jsonapi`

Sample application providing a RESTful web service following the [JSON API](http://jsonapi.org/) specification. 

Run the sample with:

    ./gradlew clean :swapi-labs-jsonapi:bootRun

Note: development not yet started. 


## Refreshing SWAPI data

The  SWAPI data is stored in `swapi-labs-data/src/main/resources/data.sql`.

To refresh the `data.sql` simply run:

    ./gradlew refreshSwapiData  


## License

This software is released under version 2.0 of the [Apache License][].


[Apache License]: http://www.apache.org/licenses/LICENSE-2.0
