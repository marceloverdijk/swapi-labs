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

package com.github.marceloverdijk.swapi.labs.springhateoas.web.controller;

import com.github.marceloverdijk.swapi.labs.model.Vehicle;
import com.github.marceloverdijk.swapi.labs.repository.VehicleRepository;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.exception.ResourceNotFoundException;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.resource.VehicleResource;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.resource.VehicleResourceAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static com.github.marceloverdijk.swapi.labs.springhateoas.web.Paths.PATH_VEHICLE_RESOURCES;
import static com.github.marceloverdijk.swapi.labs.springhateoas.web.Paths.PATH_VEHICLE_RESOURCE_BY_ID;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8_VALUE;

/**
 * The vehicle controller.
 *
 * @author Marcel Overdijk
 */
@RestController
public class VehicleController {

    private static Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);

    private final VehicleRepository vehicleRepository;
    private final VehicleResourceAssembler vehicleResourceAssembler;

    public VehicleController(final VehicleRepository vehicleRepository, final VehicleResourceAssembler vehicleResourceAssembler) {
        this.vehicleRepository = Objects.requireNonNull(vehicleRepository, "'vehicleRepository' must not be null");
        this.vehicleResourceAssembler = Objects.requireNonNull(vehicleResourceAssembler, "'vehicleResourceAssembler' must not be null");
    }

    @GetMapping(path = PATH_VEHICLE_RESOURCES, produces = HAL_JSON_UTF8_VALUE)
    public PagedResources<VehicleResource> list(Pageable pageable, PagedResourcesAssembler<Vehicle> pagedResourcesAssembler) {
        LOGGER.info("list called");
        Page<Vehicle> page = vehicleRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "name"));
        return pagedResourcesAssembler.toResource(page, vehicleResourceAssembler);
    }

    @GetMapping(path = PATH_VEHICLE_RESOURCE_BY_ID, produces = HAL_JSON_UTF8_VALUE)
    public VehicleResource getById(@PathVariable("vehicle-id") Long vehicleId) {
        LOGGER.info("get by id [{}] called", vehicleId);
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle with id [" + vehicleId + "] not found"));
        return vehicleResourceAssembler.toResource(vehicle);
    }
}
