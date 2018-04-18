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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.Links;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.RelationshipLinks;
import com.github.jasminb.jsonapi.annotations.Type;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The person resource.
 *
 * @author Marcel Overdijk
 */
@Type("person")
public class PersonResource extends BaseResource {

    private String name;
    private Integer height;
    private Double mass;
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private String birthYear;
    private String gender;

    @Relationship("homeworld")
    private PlanetResource homeworld;

    @RelationshipLinks("homeworld")
    private Links homeworldLinks;

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

    public PlanetResource getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(final PlanetResource homeworld) {
        this.homeworld = homeworld;
    }

    public Links getHomeworldLinks() {
        return homeworldLinks;
    }

    public void setHomeworldLinks(final Links homeworldLinks) {
        this.homeworldLinks = homeworldLinks;
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
