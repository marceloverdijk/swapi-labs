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
import com.github.marceloverdijk.swapi.labs.jsonapi.web.resource.FilmResource;
import com.github.marceloverdijk.swapi.labs.jsonapi.web.resource.FilmResourceAssembler;
import com.github.marceloverdijk.swapi.labs.model.Film;
import com.github.marceloverdijk.swapi.labs.repository.FilmRepository;
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
import static com.github.marceloverdijk.swapi.labs.jsonapi.web.Paths.PATH_FILM_RESOURCES;
import static com.github.marceloverdijk.swapi.labs.jsonapi.web.Paths.PATH_FILM_RESOURCE_BY_ID;

/**
 * The film controller.
 *
 * @author Marcel Overdijk
 */
@RestController
public class FilmController extends BaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(FilmController.class);

    private final FilmRepository filmRepository;
    private final FilmResourceAssembler filmResourceAssembler;

    public FilmController(final FilmRepository filmRepository, final FilmResourceAssembler filmResourceAssembler) {
        this.filmRepository = Objects.requireNonNull(filmRepository, "'filmRepository' must not be null");
        this.filmResourceAssembler = Objects.requireNonNull(filmResourceAssembler, "'filmResourceAssembler' must not be null");
    }

    @GetMapping(path = PATH_FILM_RESOURCES, produces = APPLICATION_VND_API_JSON_VALUE)
    public JSONAPIDocument<List<FilmResource>> list(Pageable pageable) {
        LOGGER.info("list called");
        Page<Film> page = filmRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "episodeId"));
        return createPagedDocument(page, filmResourceAssembler);
    }

    @GetMapping(path = PATH_FILM_RESOURCE_BY_ID, produces = APPLICATION_VND_API_JSON_VALUE)
    public JSONAPIDocument<FilmResource> getById(@PathVariable("film-id") Long filmId) {
        LOGGER.info("get by id [{}] called", filmId);
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new ResourceNotFoundException("Film with id [" + filmId + "] not found"));
        return createDocument(film, filmResourceAssembler);
    }
}
