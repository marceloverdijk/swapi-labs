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

import com.github.marceloverdijk.swapi.labs.model.Starship;
import com.github.marceloverdijk.swapi.labs.repository.StarshipRepository;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.exception.ResourceNotFoundException;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.resource.StarshipResource;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.resource.StarshipResourceAssembler;
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

import static com.github.marceloverdijk.swapi.labs.springhateoas.web.Paths.PATH_STARSHIP_RESOURCES;
import static com.github.marceloverdijk.swapi.labs.springhateoas.web.Paths.PATH_STARSHIP_RESOURCE_BY_ID;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8_VALUE;

/**
 * The starship controller.
 *
 * @author Marcel Overdijk
 */
@RestController
public class StarshipController {

    private static Logger LOGGER = LoggerFactory.getLogger(StarshipController.class);

    private final StarshipRepository starshipRepository;
    private final StarshipResourceAssembler starshipResourceAssembler;

    public StarshipController(final StarshipRepository starshipRepository, final StarshipResourceAssembler starshipResourceAssembler) {
        this.starshipRepository = Objects.requireNonNull(starshipRepository, "'starshipRepository' must not be null");
        this.starshipResourceAssembler = Objects.requireNonNull(starshipResourceAssembler, "'starshipResourceAssembler' must not be null");
    }

    @GetMapping(path = PATH_STARSHIP_RESOURCES, produces = HAL_JSON_UTF8_VALUE)
    public PagedResources<StarshipResource> list(Pageable pageable, PagedResourcesAssembler<Starship> pagedResourcesAssembler) {
        LOGGER.info("list called");
        Page<Starship> page = starshipRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "name"));
        return pagedResourcesAssembler.toResource(page, starshipResourceAssembler);
    }

    @GetMapping(path = PATH_STARSHIP_RESOURCE_BY_ID, produces = HAL_JSON_UTF8_VALUE)
    public StarshipResource getById(@PathVariable("starship-id") Long starshipId) {
        LOGGER.info("get by id [{}] called", starshipId);
        Starship starship = starshipRepository.findById(starshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Starship with id [" + starshipId + "] not found"));
        return starshipResourceAssembler.toResource(starship);
    }
}
