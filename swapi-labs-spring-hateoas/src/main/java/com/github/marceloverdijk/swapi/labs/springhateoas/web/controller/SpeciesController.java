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

import com.github.marceloverdijk.swapi.labs.model.Species;
import com.github.marceloverdijk.swapi.labs.repository.SpeciesRepository;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.exception.ResourceNotFoundException;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.resource.SpeciesResource;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.resource.SpeciesResourceAssembler;
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

import static com.github.marceloverdijk.swapi.labs.springhateoas.web.Paths.PATH_SPECIES_RESOURCES;
import static com.github.marceloverdijk.swapi.labs.springhateoas.web.Paths.PATH_SPECIES_RESOURCE_BY_ID;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8_VALUE;

/**
 * The species controller.
 *
 * @author Marcel Overdijk
 */
@RestController
public class SpeciesController {

    private static Logger LOGGER = LoggerFactory.getLogger(SpeciesController.class);

    private final SpeciesRepository speciesRepository;
    private final SpeciesResourceAssembler speciesResourceAssembler;

    public SpeciesController(final SpeciesRepository speciesRepository, final SpeciesResourceAssembler speciesResourceAssembler) {
        this.speciesRepository = Objects.requireNonNull(speciesRepository, "'speciesRepository' must not be null");
        this.speciesResourceAssembler = Objects.requireNonNull(speciesResourceAssembler, "'speciesResourceAssembler' must not be null");
    }

    @GetMapping(path = PATH_SPECIES_RESOURCES, produces = HAL_JSON_UTF8_VALUE)
    public PagedResources<SpeciesResource> list(Pageable pageable, PagedResourcesAssembler<Species> pagedResourcesAssembler) {
        LOGGER.info("list called");
        Page<Species> page = speciesRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "name"));
        return pagedResourcesAssembler.toResource(page, speciesResourceAssembler);
    }

    @GetMapping(path = PATH_SPECIES_RESOURCE_BY_ID, produces = HAL_JSON_UTF8_VALUE)
    public SpeciesResource getById(@PathVariable("species-id") Long speciesId) {
        LOGGER.info("get by id [{}] called", speciesId);
        Species species = speciesRepository.findById(speciesId)
                .orElseThrow(() -> new ResourceNotFoundException("Species with id [" + speciesId + "] not found"));
        return speciesResourceAssembler.toResource(species);
    }
}
