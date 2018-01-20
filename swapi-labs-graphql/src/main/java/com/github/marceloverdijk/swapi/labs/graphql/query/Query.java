/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.marceloverdijk.swapi.labs.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.github.marceloverdijk.swapi.labs.model.Planet;
import com.github.marceloverdijk.swapi.labs.repository.PlanetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * The query resolver.
 *
 * @author Marcel Overdijk
 */
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
