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

import com.github.marceloverdijk.swapi.labs.model.Vehicle;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.controller.VehicleController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * The vehicle resource assembler.
 *
 * @author Marcel Overdijk
 */
@Component
public class VehicleResourceAssembler extends ResourceAssemblerSupport<Vehicle, VehicleResource> {

    public VehicleResourceAssembler() {
        super(VehicleController.class, VehicleResource.class);
    }

    @Override
    public VehicleResource toResource(final Vehicle vehicle) {
        VehicleResource resource = new VehicleResource();
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
        resource.add(
                linkTo(methodOn(VehicleController.class).getById(vehicle.getId())).withSelfRel());
        return resource;
    }
}
