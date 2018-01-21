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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

/**
 * The planet resource assembler.
 *
 * @author Marcel Overdijk
 */
@Component
public class PlanetResourceAssembler implements ResourceAssembler<Planet, PlanetResource> {

    @Override
    public PlanetResource toResource(final Planet planet) {
        PlanetResource resource = new PlanetResource();
        resource.setId(planet.getId() != null ? planet.getId().toString() : null);
        resource.setName(planet.getName());
        // TODO
        return resource;
    }
}
