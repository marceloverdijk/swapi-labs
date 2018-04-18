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

import com.github.marceloverdijk.swapi.labs.model.Starship;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.controller.StarshipController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * The starship resource assembler.
 *
 * @author Marcel Overdijk
 */
@Component
public class StarshipResourceAssembler extends ResourceAssemblerSupport<Starship, StarshipResource> {

    public StarshipResourceAssembler() {
        super(StarshipController.class, StarshipResource.class);
    }

    @Override
    public StarshipResource toResource(final Starship starship) {
        StarshipResource resource = new StarshipResource();
        resource.setName(starship.getName());
        resource.setModel(starship.getModel());
        resource.setManufacturer(starship.getManufacturer());
        resource.setCostInCredits(starship.getCostInCredits());
        resource.setLength(starship.getLength());
        resource.setMaxAtmospheringSpeed(starship.getMaxAtmospheringSpeed());
        resource.setCrew(starship.getCrew());
        resource.setPassengers(starship.getPassengers());
        resource.setCargoCapacity(starship.getCargoCapacity());
        resource.setConsumables(starship.getConsumables());
        resource.setHyperdriveRating(starship.getHyperdriveRating());
        resource.setMGLT(starship.getMGLT());
        resource.setStarshipClass(starship.getStarshipClass());
        resource.add(
                linkTo(methodOn(StarshipController.class).getById(starship.getId())).withSelfRel());
        return resource;
    }
}
