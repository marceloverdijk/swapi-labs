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

package com.github.marceloverdijk.swapi.labs.jsonapi.web.controller;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.marceloverdijk.swapi.labs.jsonapi.web.exception.ResourceNotFoundException;
import com.github.marceloverdijk.swapi.labs.jsonapi.web.resource.VehicleResource;
import com.github.marceloverdijk.swapi.labs.jsonapi.web.resource.VehicleResourceAssembler;
import com.github.marceloverdijk.swapi.labs.model.Vehicle;
import com.github.marceloverdijk.swapi.labs.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static com.github.marceloverdijk.swapi.labs.jsonapi.web.MediaTypes.APPLICATION_VND_API_JSON_VALUE;
import static com.github.marceloverdijk.swapi.labs.jsonapi.web.Paths.PATH_VEHICLE_RESOURCES;
import static com.github.marceloverdijk.swapi.labs.jsonapi.web.Paths.PATH_VEHICLE_RESOURCE_BY_ID;

/**
 * The vehicle controller.
 *
 * @author Marcel Overdijk
 */
@RestController
public class VehicleController extends BaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);

    private final VehicleRepository vehicleRepository;
    private final VehicleResourceAssembler vehicleResourceAssembler;

    public VehicleController(final VehicleRepository vehicleRepository, final VehicleResourceAssembler vehicleResourceAssembler) {
        this.vehicleRepository = Objects.requireNonNull(vehicleRepository, "'vehicleRepository' must not be null");
        this.vehicleResourceAssembler = Objects.requireNonNull(vehicleResourceAssembler, "'vehicleResourceAssembler' must not be null");
    }

    @GetMapping(path = PATH_VEHICLE_RESOURCES, produces = APPLICATION_VND_API_JSON_VALUE)
    public JSONAPIDocument<List<VehicleResource>> list(Pageable pageable) {
        LOGGER.info("list called");
        Page<Vehicle> page = vehicleRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "name"));
        return createPagedDocument(page, vehicleResourceAssembler);
    }

    @GetMapping(path = PATH_VEHICLE_RESOURCE_BY_ID, produces = APPLICATION_VND_API_JSON_VALUE)
    public JSONAPIDocument<VehicleResource> getById(@PathVariable("vehicle-id") Long vehicleId) {
        LOGGER.info("get by id [{}] called", vehicleId);
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle with id [" + vehicleId + "] not found"));
        return createDocument(vehicle, vehicleResourceAssembler);
    }
}
