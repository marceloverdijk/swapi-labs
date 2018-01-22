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
import com.github.marceloverdijk.swapi.labs.model.Starship;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.github.jasminb.jsonapi.JSONAPISpecConstants.SELF;
import static com.github.marceloverdijk.swapi.labs.jsonapi.web.Paths.PATH_STARSHIP_RESOURCE_BY_ID;

/**
 * The starship resource assembler.
 *
 * @author Marcel Overdijk
 */
@Component
public class StarshipResourceAssembler extends BaseResourceAssembler<Starship, StarshipResource> {

    @Override
    protected StarshipResource toResourceInternal(final Starship starship) {
        StarshipResource resource = new StarshipResource();
        resource.setId(Objects.toString(starship.getId(), null));
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

        Map<String, Link> links = new HashMap<>();
        links.put(SELF, new Link(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(PATH_STARSHIP_RESOURCE_BY_ID).buildAndExpand(resource.getId()).toString()));
        resource.setLinks(new Links(links));

        return resource;
    }
}
