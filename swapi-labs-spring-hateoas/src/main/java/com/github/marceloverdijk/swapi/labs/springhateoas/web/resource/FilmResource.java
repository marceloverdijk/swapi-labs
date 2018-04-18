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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;

/**
 * The film resource.
 *
 * @author Marcel Overdijk
 */
public class FilmResource extends ResourceSupport {

    private String title;
    private Integer episodeId;
    private String openingCrawl;
    private String director;
    private String producer;
    private LocalDate releaseDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Integer getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(final Integer episodeId) {
        this.episodeId = episodeId;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public void setOpeningCrawl(final String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(final String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(final String producer) {
        this.producer = producer;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(final LocalDate releaseDate) {
        this.releaseDate = releaseDate;
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
