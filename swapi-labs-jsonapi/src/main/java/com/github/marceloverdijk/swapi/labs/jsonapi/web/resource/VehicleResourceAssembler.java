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
import com.github.marceloverdijk.swapi.labs.model.Vehicle;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.github.jasminb.jsonapi.JSONAPISpecConstants.SELF;
import static com.github.marceloverdijk.swapi.labs.jsonapi.web.Paths.PATH_VEHICLE_RESOURCE_BY_ID;

/**
 * The vehicle resource assembler.
 *
 * @author Marcel Overdijk
 */
@Component
public class VehicleResourceAssembler extends BaseResourceAssembler<Vehicle, VehicleResource> {

    @Override
    protected VehicleResource toResourceInternal(final Vehicle vehicle) {
        VehicleResource resource = new VehicleResource();
        resource.setId(Objects.toString(vehicle.getId(), null));
        resource.setName(vehicle.getName());
        resource.setModel(vehicle.getModel());
        resource.setManufacturer(vehicle.getManufacturer());
        resource.setCostInCredits(vehicle.getCostInCredits());
        resource.setLength(vehicle.getLength());
        resource.setMaxAtmospheringSpeed(vehicle.getMaxAtmospheringSpeed());
        resource.setCrew(vehicle.getCrew());
        resource.setPassengers(vehicle.getPassengers());
        resource.setCargoCapacity(vehicle.getCargoCapacity());
        resource.setConsumables(vehicle.getConsumables());
        resource.setVehicleClass(vehicle.getVehicleClass());

        Map<String, Link> links = new HashMap<>();
        links.put(SELF, new Link(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(PATH_VEHICLE_RESOURCE_BY_ID).buildAndExpand(resource.getId()).toString()));
        resource.setLinks(new Links(links));

        return resource;
    }
}
