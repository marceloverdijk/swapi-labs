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

package com.github.marceloverdijk.swapi.labs.jsonapi.web.resource;

import com.github.jasminb.jsonapi.Link;
import com.github.jasminb.jsonapi.Links;
import com.github.marceloverdijk.swapi.labs.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.github.jasminb.jsonapi.JSONAPISpecConstants.SELF;
import static com.github.marceloverdijk.swapi.labs.jsonapi.web.Paths.PATH_PERSON_RESOURCE_BY_ID;
import static com.github.marceloverdijk.swapi.labs.jsonapi.web.Paths.PATH_PLANET_RESOURCE_BY_ID;

/**
 * The planet resource assembler.
 *
 * @author Marcel Overdijk
 */
@Component
public class PersonResourceAssembler extends BaseResourceAssembler<Person, PersonResource> {

    private final PlanetResourceAssembler planetResourceAssembler;

    @Autowired
    public PersonResourceAssembler(final PlanetResourceAssembler planetResourceAssembler) {
        this.planetResourceAssembler = Objects.requireNonNull(planetResourceAssembler, "'planetResourceAssembler' must not be null");
    }

    @Override
    protected PersonResource toResourceInternal(final Person person) {
        PersonResource resource = new PersonResource();
        resource.setId(Objects.toString(person.getId(), null));
        resource.setName(person.getName());
        resource.setHeight(person.getHeight());
        resource.setMass(person.getMass());
        resource.setHairColor(person.getHairColor());
        resource.setSkinColor(person.getSkinColor());
        resource.setEyeColor(person.getEyeColor());
        resource.setBirthYear(person.getBirthYear());
        resource.setGender(person.getGender());
        resource.setHomeworld(planetResourceAssembler.toResource(person.getHomeworld()));

        Map<String, Link> links = new HashMap<>();
        links.put(SELF, new Link(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(PATH_PERSON_RESOURCE_BY_ID).buildAndExpand(resource.getId()).toString()));
        resource.setLinks(new Links(links));

        if (person.getHomeworld() != null) {
            Map<String, Link> homeworldLinks = new HashMap<>();
            homeworldLinks.put(SELF, new Link(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(PATH_PLANET_RESOURCE_BY_ID).buildAndExpand(resource.getHomeworld().getId()).toString()));
            resource.setHomeworldLinks(new Links(homeworldLinks));
        }

        return resource;
    }
}
