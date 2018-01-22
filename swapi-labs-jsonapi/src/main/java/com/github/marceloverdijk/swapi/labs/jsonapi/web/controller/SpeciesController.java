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

package com.github.marceloverdijk.swapi.labs.jsonapi.web.controller;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.marceloverdijk.swapi.labs.jsonapi.web.exception.ResourceNotFoundException;
import com.github.marceloverdijk.swapi.labs.jsonapi.web.resource.SpeciesResource;
import com.github.marceloverdijk.swapi.labs.jsonapi.web.resource.SpeciesResourceAssembler;
import com.github.marceloverdijk.swapi.labs.model.Species;
import com.github.marceloverdijk.swapi.labs.repository.SpeciesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static com.github.marceloverdijk.swapi.labs.jsonapi.web.MediaTypes.APPLICATION_VND_API_JSON_VALUE;
import static com.github.marceloverdijk.swapi.labs.jsonapi.web.Paths.PATH_SPECIES_RESOURCES;
import static com.github.marceloverdijk.swapi.labs.jsonapi.web.Paths.PATH_SPECIES_RESOURCE_BY_ID;

/**
 * The species controller.
 *
 * @author Marcel Overdijk
 */
@RestController
public class SpeciesController extends BaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(SpeciesController.class);

    private final SpeciesRepository speciesRepository;
    private final SpeciesResourceAssembler speciesResourceAssembler;

    public SpeciesController(final SpeciesRepository speciesRepository, final SpeciesResourceAssembler speciesResourceAssembler) {
        this.speciesRepository = Objects.requireNonNull(speciesRepository, "'speciesRepository' must not be null");
        this.speciesResourceAssembler = Objects.requireNonNull(speciesResourceAssembler, "'speciesResourceAssembler' must not be null");
    }

    @GetMapping(path = PATH_SPECIES_RESOURCES, produces = APPLICATION_VND_API_JSON_VALUE)
    public JSONAPIDocument<List<SpeciesResource>> list(Pageable pageable) {
        LOGGER.info("list called");
        Page<Species> page = speciesRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "name"));
        return createPagedDocument(page, speciesResourceAssembler);
    }

    @GetMapping(path = PATH_SPECIES_RESOURCE_BY_ID, produces = APPLICATION_VND_API_JSON_VALUE)
    public JSONAPIDocument<SpeciesResource> getById(@PathVariable("species-id") Long speciesId) {
        LOGGER.info("get by id [{}] called", speciesId);
        Species species = speciesRepository.findById(speciesId)
                .orElseThrow(() -> new ResourceNotFoundException("Species with id [" + speciesId + "] not found"));
        return createDocument(species, speciesResourceAssembler);
    }
}
