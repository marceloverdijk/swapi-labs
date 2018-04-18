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

import com.github.marceloverdijk.swapi.labs.model.Species;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.controller.SpeciesController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * The species resource assembler.
 *
 * @author Marcel Overdijk
 */
@Component
public class SpeciesResourceAssembler extends ResourceAssemblerSupport<Species, SpeciesResource> {

    private final PlanetResourceAssembler planetResourceAssembler;

    @Autowired
    public SpeciesResourceAssembler(final PlanetResourceAssembler planetResourceAssembler) {
        super(SpeciesController.class, SpeciesResource.class);
        this.planetResourceAssembler = Objects.requireNonNull(planetResourceAssembler, "'planetResourceAssembler' must not be null");
    }

    @Override
    public SpeciesResource toResource(final Species species) {
        SpeciesResource resource = new SpeciesResource();
        resource.setName(species.getName());
        resource.setClassification(species.getClassification());
        resource.setDesignation(species.getDesignation());
        resource.setAverageHeight(species.getAverageHeight());
        resource.setSkinColors(species.getSkinColors());
        resource.setHairColors(species.getHairColors());
        resource.setEyeColors(species.getEyeColors());
        resource.setAverageLifespan(species.getAverageLifespan());
        if (species.getHomeworld() != null) {
            resource.setHomeworld(planetResourceAssembler.toResource(species.getHomeworld()));
        }
        resource.setLanguage(species.getLanguage());
        resource.add(
                linkTo(methodOn(SpeciesController.class).getById(species.getId())).withSelfRel());
        // TODO add homeworld link
        return resource;
    }
}
