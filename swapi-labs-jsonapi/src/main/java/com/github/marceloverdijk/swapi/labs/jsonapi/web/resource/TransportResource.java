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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The transport resource.
 *
 * @author Marcel Overdijk
 */
public abstract class TransportResource extends BaseResource {

    private String name;
    private String model;
    private String manufacturer;
    private Long costInCredits;
    private Integer length;
    private Integer maxAtmospheringSpeed;
    private Integer crew;
    private Integer passengers;
    private Long cargoCapacity;
    private String consumables;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Long getCostInCredits() {
        return costInCredits;
    }

    public void setCostInCredits(final Long costInCredits) {
        this.costInCredits = costInCredits;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(final Integer length) {
        this.length = length;
    }

    public Integer getMaxAtmospheringSpeed() {
        return maxAtmospheringSpeed;
    }

    public void setMaxAtmospheringSpeed(final Integer maxAtmospheringSpeed) {
        this.maxAtmospheringSpeed = maxAtmospheringSpeed;
    }

    public Integer getCrew() {
        return crew;
    }

    public void setCrew(final Integer crew) {
        this.crew = crew;
    }

    public Integer getPassengers() {
        return passengers;
    }

    public void setPassengers(final Integer passengers) {
        this.passengers = passengers;
    }

    public Long getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(final Long cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public String getConsumables() {
        return consumables;
    }

    public void setConsumables(final String consumables) {
        this.consumables = consumables;
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
