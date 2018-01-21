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

import com.github.marceloverdijk.swapi.labs.model.Planet;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * The planet resource assembler.
 *
 * @author Marcel Overdijk
 */
@Component
public class PlanetResourceAssembler extends BaseResourceAssembler<Planet, PlanetResource> {

    @Override
    protected PlanetResource toResourceInternal(final Planet planet) {
        PlanetResource resource = new PlanetResource();
        resource.setId(Objects.toString(planet.getId(), null));
        resource.setName(planet.getName());
        resource.setRotationPeriod(planet.getRotationPeriod());
        resource.setOrbitalPeriod(planet.getOrbitalPeriod());
        resource.setDiameter(planet.getDiameter());
        resource.setClimate(planet.getClimate());
        resource.setGravity(planet.getGravity());
        resource.setTerrain(planet.getTerrain());
        resource.setSurfaceWater(planet.getSurfaceWater());
        resource.setPopulation(planet.getPopulation());
        // TODO add resource links
        return resource;
    }
}
