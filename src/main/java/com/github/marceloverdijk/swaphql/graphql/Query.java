package com.github.marceloverdijk.swaphql.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.github.marceloverdijk.swaphql.model.Planet;
import com.github.marceloverdijk.swaphql.repository.PlanetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class Query implements GraphQLQueryResolver {

    private static Logger LOGGER = LoggerFactory.getLogger(Query.class);

    private PlanetRepository planetRepository;

    @Autowired
    public Query(PlanetRepository planetRepository) {
        this.planetRepository = Objects.requireNonNull(planetRepository, "'planetRepository' must not be null");
    }

    public String version() {
        LOGGER.info("version called");
        return "1.0";
    }

    public List<Planet> planets(Integer page, Integer size) {
        LOGGER.info("planets called");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, "name");
        List<Planet> planets = planetRepository.findAll(pageable).getContent();
        return planets;
    }

    public Planet planet(Long planetId) {
        LOGGER.info("planet [{}] called", planetId);
        Planet planet = planetRepository.findById(planetId).orElse(null);
        return planet;
    }
}
