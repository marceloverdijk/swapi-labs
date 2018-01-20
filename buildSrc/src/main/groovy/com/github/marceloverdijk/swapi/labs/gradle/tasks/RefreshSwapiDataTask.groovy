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

package com.github.marceloverdijk.swapi.labs.gradle.tasks

import com.github.swapi4j.SwapiClient
import com.github.swapi4j.exporter.Exporter
import com.github.swapi4j.model.Film
import com.github.swapi4j.model.Person
import com.github.swapi4j.model.Planet
import com.github.swapi4j.model.ResourceUrl
import com.github.swapi4j.model.Species
import com.github.swapi4j.model.Starship
import com.github.swapi4j.model.Vehicle
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Gradle task for refreshing the SWAPI data.
 *
 * @author Marcel Overdijk
 */
class RefreshSwapiDataTask extends DefaultTask {

    String group = "SWAPI"
    String description = "Refreshes the SWAPI data."

    File dataFile = project.file("src/main/resources/data.sql")

    @TaskAction
    def refresh() {

        println "Refreshing data..."

        dataFile.delete()

        def client = new SwapiClient()
        client.export(new SwapiDataExporter(dataFile))

        println "Done"
    }
}

class SwapiDataExporter implements Exporter {

    static String LS = System.getProperty("line.separator")

    File dataFile

    SwapiDataExporter(File dataFile) {
        this.dataFile = dataFile
    }

    @Override
    void export(
            List<Person> persons,
            List<Planet> planets,
            List<Film> films,
            List<Species> species,
            List<Vehicle> vehicles,
            List<Starship> starships) {

        // Find and remove the "unknown" planet (and its references).
        def unknown = planets.find { ("unknown").equalsIgnoreCase(it.name) }.id
        planets.removeAll { it.id == unknown }
        persons.each { if (it.homeworld?.id == unknown) it.homeworld = null }
        species.each { if (it.homeworld?.id == unknown) it.homeworld = null }

        // Sort data structures.
        planets = planets.sort { it.id }
        persons = persons.sort { it.id }
        species = species.sort { it.id }
        vehicles = vehicles.sort { it.id }
        starships = starships.sort { it.id }
        films = films.sort { it.id }

        // Write sql inserts.
        writePlanetInserts(planets)
        writePersonInserts(persons)
        writeSpeciesInserts(species)
        writeVehicleInserts(vehicles)
        writeStarshipInserts(starships)
        writeFilmInserts(films)
    }

    def writePlanetInserts(List<Planet> planets) {
        planets.each {
            dataFile << "INSERT INTO planet (id, name, rotation_period, orbital_period, diameter, climate, gravity, terrain, surface_water, population, created, edited) VALUES "
            dataFile << "(" + writeNumberValue(it.id)
            dataFile << ", " + writeStringValue(it.name)
            dataFile << ", " + writeNumberValue(it.rotationPeriod)
            dataFile << ", " + writeNumberValue(it.orbitalPeriod)
            dataFile << ", " + writeNumberValue(it.diameter)
            dataFile << ", " + writeStringValue(it.climate)
            dataFile << ", " + writeStringValue(it.gravity)
            dataFile << ", " + writeStringValue(it.terrain)
            dataFile << ", " + writeNumberValue(it.surfaceWater)
            dataFile << ", " + writeNumberValue(it.population)
            dataFile << ", " + writeStringValue(it.created)
            dataFile << ", " + writeStringValue(it.edited)
            dataFile << ");"
            dataFile << LS
        }
        dataFile << LS
    }

    def writePersonInserts(List<Person> persons) {
        persons.each {
            dataFile << "INSERT INTO person (id, name, height, mass, hair_color, skin_color, eye_color, birth_year, gender, homeworld, created, edited) VALUES "
            dataFile << "(" + writeNumberValue(it.id)
            dataFile << ", " + writeStringValue(it.name)
            dataFile << ", " + writeNumberValue(it.height)
            dataFile << ", " + writeNumberValue(it.mass)
            dataFile << ", " + writeStringValue(it.hairColor)
            dataFile << ", " + writeStringValue(it.skinColor)
            dataFile << ", " + writeStringValue(it.eyeColor)
            dataFile << ", " + writeStringValue(it.birthYear)
            dataFile << ", " + writeStringValue(it.gender)
            dataFile << ", " + writeNumberValue(it.homeworld)
            dataFile << ", " + writeStringValue(it.created)
            dataFile << ", " + writeStringValue(it.edited)
            dataFile << ");"
            dataFile << LS
        }
        dataFile << LS
    }

    def writeSpeciesInserts(List<Species> species) {
        species.each {
            dataFile << "INSERT INTO species (id, name, classification, designation, average_height, skin_colors, hair_colors, eye_colors, average_lifespan, homeworld, language, created, edited) VALUES "
            dataFile << "(" + writeNumberValue(it.id)
            dataFile << ", " + writeStringValue(it.name)
            dataFile << ", " + writeStringValue(it.classification)
            dataFile << ", " + writeStringValue(it.designation)
            dataFile << ", " + writeNumberValue(it.averageHeight)
            dataFile << ", " + writeStringValue(it.skinColors)
            dataFile << ", " + writeStringValue(it.hairColors)
            dataFile << ", " + writeStringValue(it.eyeColors)
            dataFile << ", " + writeNumberValue(it.averageLifespan)
            dataFile << ", " + writeNumberValue(it.homeworld)
            dataFile << ", " + writeStringValue(it.language)
            dataFile << ", " + writeStringValue(it.created)
            dataFile << ", " + writeStringValue(it.edited)
            dataFile << ");"
            dataFile << LS
        }
        dataFile << LS

        // TODO intersection tables
    }

    def writeVehicleInserts(List<Vehicle> vehicles) {
        vehicles.each {
            dataFile << "INSERT INTO vehicle (id, name, model, manufacturer, cost_in_credits, length, max_atmosphering_speed, crew, passengers, cargo_capacity, consumables, vehicle_class, created, edited) VALUES "
            dataFile << "(" + writeNumberValue(it.id)
            dataFile << ", " + writeStringValue(it.name)
            dataFile << ", " + writeStringValue(it.model)
            dataFile << ", " + writeStringValue(it.manufacturer)
            dataFile << ", " + writeNumberValue(it.costInCredits)
            dataFile << ", " + writeNumberValue(it.length)
            dataFile << ", " + writeNumberValue(it.maxAtmospheringSpeed)
            dataFile << ", " + writeNumberValue(it.crew)
            dataFile << ", " + writeNumberValue(it.passengers)
            dataFile << ", " + writeNumberValue(it.cargoCapacity)
            dataFile << ", " + writeStringValue(it.consumables)
            dataFile << ", " + writeStringValue(it.vehicleClass)
            dataFile << ", " + writeStringValue(it.created)
            dataFile << ", " + writeStringValue(it.edited)
            dataFile << ");"
            dataFile << LS
        }
        dataFile << LS

        // TODO intersection tables
    }

    def writeStarshipInserts(List<Starship> starships) {
        starships.each {
            dataFile << "INSERT INTO starship (id, name, model, manufacturer, cost_in_credits, length, max_atmosphering_speed, crew, passengers, cargo_capacity, consumables, hyperdrive_rating, mglt, starship_class, created, edited) VALUES "
            dataFile << "(" + writeNumberValue(it.id)
            dataFile << ", " + writeStringValue(it.name)
            dataFile << ", " + writeStringValue(it.model)
            dataFile << ", " + writeStringValue(it.manufacturer)
            dataFile << ", " + writeNumberValue(it.costInCredits)
            dataFile << ", " + writeNumberValue(it.length)
            dataFile << ", " + writeNumberValue(it.maxAtmospheringSpeed)
            dataFile << ", " + writeNumberValue(it.crew)
            dataFile << ", " + writeNumberValue(it.passengers)
            dataFile << ", " + writeNumberValue(it.cargoCapacity)
            dataFile << ", " + writeStringValue(it.consumables)
            dataFile << ", " + writeNumberValue(it.hyperdriveRating)
            dataFile << ", " + writeNumberValue(it.MGLT)
            dataFile << ", " + writeStringValue(it.starshipClass)
            dataFile << ", " + writeStringValue(it.created)
            dataFile << ", " + writeStringValue(it.edited)
            dataFile << ");"
            dataFile << LS
        }
        dataFile << LS

        // TODO intersection tables
    }

    def writeFilmInserts(List<Film> films) {
        films.each {
            dataFile << "INSERT INTO film (id, title, episode_id, opening_crawl, director, producer, release_date, created, edited) VALUES"
            dataFile << "(" + writeNumberValue(it.id)
            dataFile << ", " + writeStringValue(it.title)
            dataFile << ", " + writeNumberValue(it.episodeId)
            dataFile << ", " + writeStringValue(it.openingCrawl)
            dataFile << ", " + writeStringValue(it.director)
            dataFile << ", " + writeStringValue(it.producer)
            dataFile << ", " + writeStringValue(it.releaseDate)
            dataFile << ", " + writeStringValue(it.created)
            dataFile << ", " + writeStringValue(it.edited)
            dataFile << ");"
            dataFile << LS
        }
        dataFile << LS

        // TODO intersection tables
    }

    def isNotUnknown(s) {
        return !("unknown".equalsIgnoreCase(s) || "n/a".equalsIgnoreCase(s))
    }

    def writeStringValue(value) {
        def result = "NULL"
        if (value) {
            if (!["unknown", "n/a"].contains(value)) {
                result = value.replaceAll (/'/,/''/) // Escape quotes (by doubling them).
                result = "'${result}'" // Quote the string value.
            }
        }
        return result
    }

    def writeNumberValue(value) {
        def result = "NULL"
        if (value) {
            if (!["unknown", "none", "n/a", "indefinite"].contains(value)) {
                if (value instanceof ResourceUrl) {
                    result = value.id
                } else if (value instanceof String) {
                    if (value.endsWith("km")) {
                        value = value.substring(0, value.length() - 2) // Remove 'km' suffix.
                    }
                    result = value.replaceAll (/,/,/./) // Replace comma with dot (change decimal separator).
                } else {
                    result = value
                }
            }
        }
        return result
    }
}
