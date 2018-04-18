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

package com.github.marceloverdijk.swapi.labs.springhateoas.web.controller;

import com.github.marceloverdijk.swapi.labs.model.Planet;
import com.github.marceloverdijk.swapi.labs.repository.PlanetRepository;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.exception.ResourceNotFoundException;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.resource.PlanetResource;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.resource.PlanetResourceAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static com.github.marceloverdijk.swapi.labs.springhateoas.web.Paths.PATH_PLANET_RESOURCES;
import static com.github.marceloverdijk.swapi.labs.springhateoas.web.Paths.PATH_PLANET_RESOURCE_BY_ID;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8_VALUE;

/**
 * The planet controller.
 *
 * @author Marcel Overdijk
 */
@RestController
public class PlanetController {

    private static Logger LOGGER = LoggerFactory.getLogger(PlanetController.class);

    private final PlanetRepository planetRepository;
    private final PlanetResourceAssembler planetResourceAssembler;

    public PlanetController(final PlanetRepository planetRepository, final PlanetResourceAssembler planetResourceAssembler) {
        this.planetRepository = Objects.requireNonNull(planetRepository, "'planetRepository' must not be null");
        this.planetResourceAssembler = Objects.requireNonNull(planetResourceAssembler, "'planetResourceAssembler' must not be null");
    }

    @GetMapping(path = PATH_PLANET_RESOURCES, produces = HAL_JSON_UTF8_VALUE)
    public PagedResources<PlanetResource> list(Pageable pageable, PagedResourcesAssembler<Planet> pagedResourcesAssembler) {
        LOGGER.info("list called");
        Page<Planet> page = planetRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "name"));
        return pagedResourcesAssembler.toResource(page, planetResourceAssembler);
    }

    @GetMapping(path = PATH_PLANET_RESOURCE_BY_ID, produces = HAL_JSON_UTF8_VALUE)
    public PlanetResource getById(@PathVariable("planet-id") Long planetId) {
        LOGGER.info("get by id [{}] called", planetId);
        Planet planet = planetRepository.findById(planetId)
                .orElseThrow(() -> new ResourceNotFoundException("Planet with id [" + planetId + "] not found"));
        return planetResourceAssembler.toResource(planet);
    }
}
