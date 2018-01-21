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

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

/**
 * Represents a starship.
 *
 * @author Marcel Overdijk
 */
@Entity
@Table(name = "starship")
@AssociationOverrides({
        @AssociationOverride(
                name = "pilots",
                joinTable = @JoinTable(
                        name = "starship_pilot",
                        joinColumns = @JoinColumn(name = "starship_id", referencedColumnName = "id"),
                        inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))),
        @AssociationOverride(
                name = "films",
                joinTable = @JoinTable(
                        name = "film_starship",
                        joinColumns = @JoinColumn(name = "starship_id", referencedColumnName = "id"),
                        inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id")))})
public class Starship extends Transport {

    @Column(name = "hyperdrive_rating")
    private Double hyperdriveRating;

    @Column(name = "mglt")
    private Integer MGLT;

    @Column(name = "starship_class")
    private String starshipClass;

    public Double getHyperdriveRating() {
        return hyperdriveRating;
    }

    public void setHyperdriveRating(final Double hyperdriveRating) {
        this.hyperdriveRating = hyperdriveRating;
    }

    public Integer getMGLT() {
        return MGLT;
    }

    public void setMGLT(final Integer MGLT) {
        this.MGLT = MGLT;
    }

    public String getStarshipClass() {
        return starshipClass;
    }

    public void setStarshipClass(final String starshipClass) {
        this.starshipClass = starshipClass;
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
