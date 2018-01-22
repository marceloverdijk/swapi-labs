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
import com.github.marceloverdijk.swapi.labs.jsonapi.web.resource.StarshipResource;
import com.github.marceloverdijk.swapi.labs.jsonapi.web.resource.StarshipResourceAssembler;
import com.github.marceloverdijk.swapi.labs.model.Starship;
import com.github.marceloverdijk.swapi.labs.repository.StarshipRepository;
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
import static com.github.marceloverdijk.swapi.labs.jsonapi.web.Paths.PATH_STARSHIP_RESOURCES;
import static com.github.marceloverdijk.swapi.labs.jsonapi.web.Paths.PATH_STARSHIP_RESOURCE_BY_ID;

/**
 * The starship controller.
 *
 * @author Marcel Overdijk
 */
@RestController
public class StarshipController extends BaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(StarshipController.class);

    private final StarshipRepository starshipRepository;
    private final StarshipResourceAssembler starshipResourceAssembler;

    public StarshipController(final StarshipRepository starshipRepository, final StarshipResourceAssembler starshipResourceAssembler) {
        this.starshipRepository = Objects.requireNonNull(starshipRepository, "'starshipRepository' must not be null");
        this.starshipResourceAssembler = Objects.requireNonNull(starshipResourceAssembler, "'starshipResourceAssembler' must not be null");
    }

    @GetMapping(path = PATH_STARSHIP_RESOURCES, produces = APPLICATION_VND_API_JSON_VALUE)
    public JSONAPIDocument<List<StarshipResource>> list(Pageable pageable) {
        LOGGER.info("list called");
        Page<Starship> page = starshipRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "name"));
        return createPagedDocument(page, starshipResourceAssembler);
    }

    @GetMapping(path = PATH_STARSHIP_RESOURCE_BY_ID, produces = APPLICATION_VND_API_JSON_VALUE)
    public JSONAPIDocument<StarshipResource> getById(@PathVariable("starship-id") Long starshipId) {
        LOGGER.info("get by id [{}] called", starshipId);
        Starship starship = starshipRepository.findById(starshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Starship with id [" + starshipId + "] not found"));
        return createDocument(starship, starshipResourceAssembler);
    }
}
