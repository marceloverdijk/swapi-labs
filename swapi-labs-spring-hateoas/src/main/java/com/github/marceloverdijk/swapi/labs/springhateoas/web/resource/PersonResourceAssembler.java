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

package com.github.marceloverdijk.swapi.labs.springhateoas.web.resource;

import com.github.marceloverdijk.swapi.labs.model.Person;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.controller.PersonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * The planet resource assembler.
 *
 * @author Marcel Overdijk
 */
@Component
public class PersonResourceAssembler extends ResourceAssemblerSupport<Person, PersonResource> {

    private final PlanetResourceAssembler planetResourceAssembler;

    @Autowired
    public PersonResourceAssembler(final PlanetResourceAssembler planetResourceAssembler) {
        super(PersonController.class, PersonResource.class);
        this.planetResourceAssembler = Objects.requireNonNull(planetResourceAssembler, "'planetResourceAssembler' must not be null");
    }

    @Override
    public PersonResource toResource(final Person person) {
        PersonResource resource = new PersonResource();
        resource.setName(person.getName());
        resource.setHeight(person.getHeight());
        resource.setMass(person.getMass());
        resource.setHairColor(person.getHairColor());
        resource.setSkinColor(person.getSkinColor());
        resource.setEyeColor(person.getEyeColor());
        resource.setBirthYear(person.getBirthYear());
        resource.setGender(person.getGender());
        if (person.getHomeworld() != null) {
            resource.setHomeworld(planetResourceAssembler.toResource(person.getHomeworld()));
        }
        resource.add(
                linkTo(methodOn(PersonController.class).getById(person.getId())).withSelfRel());
        // TODO add homeworld link
        return resource;
    }
}
