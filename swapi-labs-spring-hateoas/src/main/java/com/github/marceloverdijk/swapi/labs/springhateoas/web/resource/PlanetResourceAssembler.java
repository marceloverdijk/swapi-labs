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

import com.github.marceloverdijk.swapi.labs.model.Planet;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.controller.PlanetController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * The planet resource assembler.
 *
 * @author Marcel Overdijk
 */
@Component
public class PlanetResourceAssembler extends ResourceAssemblerSupport<Planet, PlanetResource> {

    public PlanetResourceAssembler() {
        super(PlanetController.class, PlanetResource.class);
    }

    @Override
    public PlanetResource toResource(final Planet planet) {
        PlanetResource resource = new PlanetResource();
        resource.setName(planet.getName());
        resource.setRotationPeriod(planet.getRotationPeriod());
        resource.setOrbitalPeriod(planet.getOrbitalPeriod());
        resource.setDiameter(planet.getDiameter());
        resource.setClimate(planet.getClimate());
        resource.setGravity(planet.getGravity());
        resource.setTerrain(planet.getTerrain());
        resource.setSurfaceWater(planet.getSurfaceWater());
        resource.setPopulation(planet.getPopulation());
        resource.add(
                linkTo(methodOn(PlanetController.class).getById(planet.getId())).withSelfRel());
        return resource;
    }
}
