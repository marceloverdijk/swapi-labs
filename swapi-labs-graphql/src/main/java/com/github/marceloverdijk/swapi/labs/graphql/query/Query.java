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

package com.github.marceloverdijk.swapi.labs.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.github.marceloverdijk.swapi.labs.model.Film;
import com.github.marceloverdijk.swapi.labs.model.Person;
import com.github.marceloverdijk.swapi.labs.model.Planet;
import com.github.marceloverdijk.swapi.labs.model.Species;
import com.github.marceloverdijk.swapi.labs.model.Starship;
import com.github.marceloverdijk.swapi.labs.model.Vehicle;
import com.github.marceloverdijk.swapi.labs.repository.FilmRepository;
import com.github.marceloverdijk.swapi.labs.repository.PersonRepository;
import com.github.marceloverdijk.swapi.labs.repository.PlanetRepository;
import com.github.marceloverdijk.swapi.labs.repository.SpeciesRepository;
import com.github.marceloverdijk.swapi.labs.repository.StarshipRepository;
import com.github.marceloverdijk.swapi.labs.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * The query resolver.
 *
 * @author Marcel Overdijk
 */
@Component
public class Query implements GraphQLQueryResolver {

    private static Logger LOGGER = LoggerFactory.getLogger(Query.class);

    private final PersonRepository personRepository;
    private final PlanetRepository planetRepository;
    private final FilmRepository filmRepository;
    private final SpeciesRepository speciesRepository;
    private final VehicleRepository vehicleRepository;
    private final StarshipRepository starshipRepository;

    @Autowired
    public Query(final PersonRepository personRepository, final PlanetRepository planetRepository, final FilmRepository filmRepository,
            final SpeciesRepository speciesRepository, final VehicleRepository vehicleRepository,
            final StarshipRepository starshipRepository) {
        this.personRepository = Objects.requireNonNull(personRepository, "'personRepository' must not be null");
        this.planetRepository = Objects.requireNonNull(planetRepository, "'planetRepository' must not be null");
        this.filmRepository = Objects.requireNonNull(filmRepository, "'filmRepository' must not be null");
        this.speciesRepository = Objects.requireNonNull(speciesRepository, "'speciesRepository' must not be null");
        this.vehicleRepository = Objects.requireNonNull(vehicleRepository, "'vehicleRepository' must not be null");
        this.starshipRepository = Objects.requireNonNull(starshipRepository, "'starshipRepository' must not be null");
    }

    public String version() {
        LOGGER.info("version called");
        return "1.0";
    }

    public List<Person> allPersons(Integer page, Integer size) {
        LOGGER.info("persons called");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, "name");
        List<Person> persons = personRepository.findAll(pageable).getContent();
        return persons;
    }

    public Person person(Long personId) {
        LOGGER.info("person [{}] called", personId);
        Person person = personRepository.findById(personId).orElse(null);
        return person;
    }

    public List<Planet> allPlanets(Integer page, Integer size) {
        LOGGER.info("planets called");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, "name");
        List<Planet> planets = planetRepository.findAll(pageable).getContent();
        return planets;
    }

    public Planet planet(Long planetId) {
        LOGGER.info("planet [{}] called", planetId);
        Planet planet = planetRepository.findById(planetId).orElse(null);
        return planet;
    }

    public List<Film> allFilms(Integer page, Integer size) {
        LOGGER.info("films called");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, "title");
        List<Film> films = filmRepository.findAll(pageable).getContent();
        return films;
    }

    public Film film(Long filmId) {
        LOGGER.info("film [{}] called", filmId);
        Film film = filmRepository.findById(filmId).orElse(null);
        return film;
    }

    public List<Species> allSpecies(Integer page, Integer size) {
        LOGGER.info("species called");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, "name");
        List<Species> species = speciesRepository.findAll(pageable).getContent();
        return species;
    }

    public Species species(Long speciesId) {
        LOGGER.info("species [{}] called", speciesId);
        Species species = speciesRepository.findById(speciesId).orElse(null);
        return species;
    }

    public List<Vehicle> allVehicles(Integer page, Integer size) {
        LOGGER.info("vehicles called");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, "name");
        List<Vehicle> vehicles = vehicleRepository.findAll(pageable).getContent();
        return vehicles;
    }

    public Vehicle vehicle(Long vehicleId) {
        LOGGER.info("vehicle [{}] called", vehicleId);
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
        return vehicle;
    }

    public List<Starship> allStarships(Integer page, Integer size) {
        LOGGER.info("starships called");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, "name");
        List<Starship> starships = starshipRepository.findAll(pageable).getContent();
        return starships;
    }

    public Starship starship(Long starshipId) {
        LOGGER.info("starship [{}] called", starshipId);
        Starship starship = starshipRepository.findById(starshipId).orElse(null);
        return starship;
    }
}
