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
 * The species resource.
 *
 * @author Marcel Overdijk
 */
@Type("species")
public class SpeciesResource extends BaseResource {

    private String name;
    private String classification;
    private String designation;
    private Integer averageHeight;
    private String skinColors;
    private String hairColors;
    private String eyeColors;
    private Integer averageLifespan;

    @Relationship("homeworld")
    private PlanetResource homeworld;

    @RelationshipLinks("homeworld")
    private Links homeworldLinks;

    private String language;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(final String classification) {
        this.classification = classification;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(final String designation) {
        this.designation = designation;
    }

    public Integer getAverageHeight() {
        return averageHeight;
    }

    public void setAverageHeight(final Integer averageHeight) {
        this.averageHeight = averageHeight;
    }

    public String getSkinColors() {
        return skinColors;
    }

    public void setSkinColors(final String skinColors) {
        this.skinColors = skinColors;
    }

    public String getHairColors() {
        return hairColors;
    }

    public void setHairColors(final String hairColors) {
        this.hairColors = hairColors;
    }

    public String getEyeColors() {
        return eyeColors;
    }

    public void setEyeColors(final String eyeColors) {
        this.eyeColors = eyeColors;
    }

    public Integer getAverageLifespan() {
        return averageLifespan;
    }

    public void setAverageLifespan(final Integer averageLifespan) {
        this.averageLifespan = averageLifespan;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
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
