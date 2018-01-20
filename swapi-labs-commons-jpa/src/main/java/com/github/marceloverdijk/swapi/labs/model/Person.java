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
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Represents a person.
 *
 * @author Marcel Overdijk
 */
@Entity
@Table(name = "person")
public class Person extends BaseModel<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "height")
    private Integer height;

    @Column(name = "mass")
    private Double mass;

    @Column(name = "hair_color")
    private String hairColor;

    @Column(name = "skin_color")
    private String skinColor;

    @Column(name = "eye_color")
    private String eyeColor;

    @Column(name = "birth_year")
    private String birthYear;

    @Column(name = "gender")
    private String gender; // TODO enum?

    @ManyToOne
    @JoinColumn(name = "homeworld")
    private Planet homeworld;

    // TODO
    private List<Film> films;

    // TODO
    private List<Species> species;

    // TODO
    private List<Vehicle> vehicles;

    // TODO
    private List<Starship> starships;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(final Integer height) {
        this.height = height;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(final Double mass) {
        this.mass = mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(final String hairColor) {
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(final String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(final String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(final String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    public Planet getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(final Planet homeworld) {
        this.homeworld = homeworld;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(final List<Film> films) {
        this.films = films;
    }

    public List<Species> getSpecies() {
        return species;
    }

    public void setSpecies(final List<Species> species) {
        this.species = species;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(final List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Starship> getStarships() {
        return starships;
    }

    public void setStarships(final List<Starship> starships) {
        this.starships = starships;
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
