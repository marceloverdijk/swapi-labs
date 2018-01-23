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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 * Represents a film.
 *
 * @author Marcel Overdijk
 */
@Entity
@Table(name = "film")
public class Film extends BaseModel<Long> {

    @Column(name = "title")
    private String title;

    @Column(name = "episode_id")
    private Integer episodeId;

    @Column(name = "opening_crawl")
    private String openingCrawl;

    @Column(name = "director")
    private String director;

    @Column(name = "producer")
    private String producer;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToMany
    @JoinTable(
            name = "film_character",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private List<Person> characters;

    @ManyToMany
    @JoinTable(
            name = "film_planet",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "planet_id", referencedColumnName = "id"))
    private List<Planet> planets;

    @ManyToMany
    @JoinTable(
            name = "film_starship",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "starship_id", referencedColumnName = "id"))
    private List<Starship> starships;

    @ManyToMany
    @JoinTable(
            name = "film_vehicle",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id", referencedColumnName = "id"))
    private List<Vehicle> vehicles;

    @ManyToMany
    @JoinTable(
            name = "film_species",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "species_id", referencedColumnName = "id"))
    private List<Species> species;

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

    public List<Person> getCharacters() {
        return characters;
    }

    public void setCharacters(final List<Person> characters) {
        this.characters = characters;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(final List<Planet> planets) {
        this.planets = planets;
    }

    public List<Starship> getStarships() {
        return starships;
    }

    public void setStarships(final List<Starship> starships) {
        this.starships = starships;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(final List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Species> getSpecies() {
        return species;
    }

    public void setSpecies(final List<Species> species) {
        this.species = species;
    }
}
