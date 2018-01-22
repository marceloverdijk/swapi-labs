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
import com.github.marceloverdijk.swapi.labs.jsonapi.web.resource.PersonResource;
import com.github.marceloverdijk.swapi.labs.jsonapi.web.resource.PersonResourceAssembler;
import com.github.marceloverdijk.swapi.labs.model.Person;
import com.github.marceloverdijk.swapi.labs.repository.PersonRepository;
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
import static com.github.marceloverdijk.swapi.labs.jsonapi.web.Paths.PATH_PERSON_RESOURCES;
import static com.github.marceloverdijk.swapi.labs.jsonapi.web.Paths.PATH_PERSON_RESOURCE_BY_ID;

/**
 * The person controller.
 *
 * @author Marcel Overdijk
 */
@RestController
public class PersonController extends BaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    private final PersonRepository personRepository;
    private final PersonResourceAssembler personResourceAssembler;

    public PersonController(final PersonRepository personRepository, final PersonResourceAssembler personResourceAssembler) {
        this.personRepository = Objects.requireNonNull(personRepository, "'personRepository' must not be null");
        this.personResourceAssembler = Objects.requireNonNull(personResourceAssembler, "'personResourceAssembler' must not be null");
    }

    @GetMapping(path = PATH_PERSON_RESOURCES, produces = APPLICATION_VND_API_JSON_VALUE)
    public JSONAPIDocument<List<PersonResource>> list(Pageable pageable) {
        LOGGER.info("list called");
        Page<Person> page = personRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "name"));
        return createPagedDocument(page, personResourceAssembler);
    }

    @GetMapping(path = PATH_PERSON_RESOURCE_BY_ID, produces = APPLICATION_VND_API_JSON_VALUE)
    public JSONAPIDocument<PersonResource> getById(@PathVariable("person-id") Long personId) {
        LOGGER.info("get by id [{}] called", personId);
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person with id [" + personId + "] not found"));
        return createDocument(person, personResourceAssembler);
    }
}
