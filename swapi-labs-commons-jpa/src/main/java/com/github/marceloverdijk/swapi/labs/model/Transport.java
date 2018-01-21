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

package com.github.marceloverdijk.swapi.labs.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.List;

/**
 * Represents a transport.
 *
 * @author Marcel Overdijk
 */
@MappedSuperclass
public abstract class Transport extends BaseModel<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "cost_in_credits")
    private Long costInCredits;

    @Column(name = "length")
    private Integer length;

    @Column(name = "max_atmosphering_speed")
    private Integer maxAtmospheringSpeed;

    @Column(name = "crew")
    private Integer crew;

    @Column(name = "passengers")
    private Integer passengers;

    @Column(name = "cargo_capacity")
    private Long cargoCapacity;

    @Column(name = "consumables")
    private String consumables;

    @ManyToMany
    private List<Person> pilots;

    @ManyToMany
    private List<Film> films;

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

    public List<Person> getPilots() {
        return pilots;
    }

    public void setPilots(final List<Person> pilots) {
        this.pilots = pilots;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(final List<Film> films) {
        this.films = films;
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
