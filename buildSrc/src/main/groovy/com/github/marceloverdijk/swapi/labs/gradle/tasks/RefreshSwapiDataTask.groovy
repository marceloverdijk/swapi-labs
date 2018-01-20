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
    Long unknownPlanetId

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

        // Find and remove the "unknown" planet.
        unknownPlanetId = planets.find { ("unknown").equalsIgnoreCase(it.name) }.id
        planets.removeAll { it.id == unknownPlanetId }

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
        dataFile << "INSERT INTO planet"
        dataFile << "  (id, name, rotation_period, orbital_period, diameter, climate, gravity, terrain, surface_water, population, created, edited)"
        dataFile << "VALUES"
        planets.eachWithIndex { it, i ->
            if (i > 0) dataFile << ","
            dataFile << LS
            dataFile << "  "
            dataFile << "(" + it.id
            dataFile << ", '" + sanitize(it.name) + "'"
            dataFile << ")"
            // TODO
        }
        dataFile << ";"
        dataFile << LS
        dataFile << LS
    }

    def writePersonInserts(List<Person> persons) {
        dataFile << "INSERT INTO person"
        dataFile << "  (id, name, height, mass, hair_color, skin_color, eye_color, birth_year, gender, homeworld, created, edited)"
        dataFile << "VALUES"
        persons.eachWithIndex { it, i ->
            if (i > 0) dataFile << ","
            dataFile << LS
            dataFile << "  "
            dataFile << "(" + it.id
            dataFile << ", '" + sanitize(it.name) + "'"
            dataFile << ")"
            // TODO
        }
        dataFile << ";"
        dataFile << LS
        dataFile << LS
    }

    def writeSpeciesInserts(List<Species> species) {
        dataFile << "INSERT INTO species"
        dataFile << "  (id, name, classification, designation, average_height, skin_colors, hair_colors, eye_colors, average_lifespan, homeworld, language, created, edited)"
        dataFile << "VALUES"
        species.eachWithIndex { it, i ->
            if (i > 0) dataFile << ","
            dataFile << LS
            dataFile << "  "
            dataFile << "(" + it.id
            dataFile << ", '" + sanitize(it.name) + "'"
            dataFile << ")"
            // TODO
        }
        dataFile << ";"
        dataFile << LS
        dataFile << LS

        // TODO intersection tables
    }

    def writeVehicleInserts(List<Vehicle> vehicles) {
        dataFile << "INSERT INTO vehicle"
        dataFile << "  (id, name, model, manufacturer, cost_in_credits, length, max_atmosphering_speed, crew, passengers, cargo_capacity, consumables, vehicle_class, created, edited)"
        dataFile << "VALUES"
        vehicles.eachWithIndex { it, i ->
            if (i > 0) dataFile << ","
            dataFile << LS
            dataFile << "  "
            dataFile << "(" + it.id
            dataFile << ", '" + sanitize(it.name) + "'"
            dataFile << ")"
            // TODO
        }
        dataFile << ";"
        dataFile << LS
        dataFile << LS

        // TODO intersection tables
    }

    def writeStarshipInserts(List<Starship> starships) {
        dataFile << "INSERT INTO starship"
        dataFile << "  (id, name, model, manufacturer, cost_in_credits, length, max_atmosphering_speed, crew, passengers, cargo_capacity, consumables, hyperdrive_rating, mglt, starship_class, created)"
        dataFile << "VALUES"
        starships.eachWithIndex { it, i ->
            if (i > 0) dataFile << ","
            dataFile << LS
            dataFile << "  "
            dataFile << "(" + it.id
            dataFile << ", '" + sanitize(it.name) + "'"
            dataFile << ")"
            // TODO
        }
        dataFile << ";"
        dataFile << LS
        dataFile << LS

        // TODO intersection tables
    }

    def writeFilmInserts(List<Film> films) {
        dataFile << "INSERT INTO film"
        dataFile << "  (id, title, episode_id, opening_crawl, director, producer, release_date, created, edited)"
        dataFile << "VALUES"
        films.eachWithIndex { it, i ->
            if (i > 0) dataFile << ","
            dataFile << LS
            dataFile << "  "
            dataFile << "(" + it.id
            dataFile << ", '" + sanitize(it.title) + "'"
            dataFile << ")"
            // TODO
        }
        dataFile << ";"
        dataFile << LS
        dataFile << LS

        // TODO intersection tables
    }

    def sanitize(String s) {
        def sanitized = s
        if ("unknown".equalsIgnoreCase(s) || "n/a".equalsIgnoreCase(s)) {
            sanitized = null
        }
        if (sanitized) {
            // Escape quotes
            sanitized = sanitized.replaceAll (/'/,/''/)
        }
    }

    def calculateMaxLengths(maxLengths, obj) {
        obj.properties.keySet().each { k ->
            if (k == "class") return
            def v = obj.properties[k]
            def length
            if (!v) {
                length = 0
            } else if (v instanceof List) {
                length = v.size()
            } else if (v instanceof Long) {
                length = v
            } else if (v instanceof Double) {
                length = v.longValue()
            } else if (v instanceof String) {
                if (v.isDouble()) {
                    length = Double.parseDouble(v).longValue()
                } else if (v.isLong()) {
                    length = Long.parseLong(v)
                } else {
                    if ("unknown".equalsIgnoreCase(v) || "n/a".equalsIgnoreCase(v)) {
                        length = 0
                    } else {
                        length = v.length()
                    }
                }
            } else {
                if (v) {
                    length = v.toString().length()
                } else {
                    length = 0
                }
            }
            if (!maxLengths[k] || length > maxLengths[k]) {
                maxLengths[k] = length
            }
        }
    }

    def printMaxLengths(name, maxLengths) {
        println "${name}:"
        maxLengths.each { k, v ->
            println "  - ${k} = ${v}"
        }
    }

//    def writePeople(people) {
//        new File(outputDir, "people.yml").withWriter { out ->
//            people.each { person ->
//                def homeworld = person.homeworld ? parseId(person.homeworld) : null
//                if (homeworld == unknownPlanetId) {
//                    // Remove reference to the "unknown" planet and instead use "null".
//                    homeworld = null
//                }
//                def properties = [
//                        "id": parseId(person.url),
//                        "name": person.name,
//                        "height": ignoreUnknown(person.height),
//                        "mass": ignoreUnknown(person.mass),
//                        "hair_color": ignoreUnknown(person.hair_color),
//                        "skin_color": ignoreUnknown(person.skin_color),
//                        "eye_color": ignoreUnknown(person.eye_color),
//                        "birth_year": ignoreUnknown(person.birth_year),
//                        "gender": ignoreUnknown(person.gender),
//                        "homeworld": homeworld,
//                        "films": getIds(person.films),
//                        "species": getIds(person.species),
//                        "vehicles": getIds(person.vehicles),
//                        "starships": getIds(person.starships),
//                        "created": person.created,
//                        "edited": person.edited
//                ]
//                writeEntry(out, properties)
//            }
//        }
//    }
//
//    def writePlanets(planets) {
//        new File(outputDir, "planets.yml").withWriter { out ->
//            planets.each { planet ->
//                def properties = [
//                        "id": parseId(planet.url),
//                        "name": planet.name,
//                        "rotation_period": ignoreUnknown(planet.rotation_period),
//                        "orbital_period": ignoreUnknown(planet.orbital_period),
//                        "diameter": ignoreUnknown(planet.diameter),
//                        "climate": ignoreUnknown(planet.climate),
//                        "gravity": ignoreUnknown(planet.gravity),
//                        "terrain": ignoreUnknown(planet.terrain),
//                        "surface_water": ignoreUnknown(planet.surface_water),
//                        "population": ignoreUnknown(planet.population),
//                        "residents": getIds(planet.residents),
//                        "films": getIds(planet.films),
//                        "created": planet.created,
//                        "edited": planet.edited
//                ]
//                writeEntry(out, properties)
//            }
//        }
//    }
//
//    def writeFilms(films) {
//        new File(outputDir, "films.yml").withWriter { out ->
//            films.each { film ->
//                def properties = [
//                        "id": parseId(film.url),
//                        "title": film.title,
//                        "episode_id": film.episode_id,
//                        "opening_crawl": film.opening_crawl ? "|- ${film.opening_crawl}" : null,
//                        "director": ignoreUnknown(film.director),
//                        "producer": ignoreUnknown(film.producer),
//                        "release_date": ignoreUnknown(film.release_date),
//                        "characters": getIds(film.characters),
//                        "planets": getIds(film.planets),
//                        "starships": getIds(film.starships),
//                        "vehicles": getIds(film.vehicles),
//                        "species": getIds(film.species),
//                        "created": film.created,
//                        "edited": film.edited
//                ]
//                writeEntry(out, properties)
//            }
//        }
//    }
//
//    def writeSpecies(species) {
//        new File(outputDir, "species.yml").withWriter { out ->
//            species.each { specie ->
//                def properties = [
//                        "id": parseId(specie.url),
//                        "name": specie.name,
//                        "classification": ignoreUnknown(specie.classification),
//                        "designation": ignoreUnknown(specie.designation),
//                        "average_height": ignoreUnknown(specie.average_height),
//                        "skin_colors": ignoreUnknown(specie.skin_colors),
//                        "hair_colors": ignoreUnknown(specie.hair_colors),
//                        "eye_colors": ignoreUnknown(specie.eye_colors),
//                        "average_lifespan": ignoreUnknown(specie.average_lifespan),
//                        "homeworld": specie.homeworld ? parseId(specie.homeworld) : null,
//                        "language": ignoreUnknown(specie.language),
//                        "people": getIds(specie.people),
//                        "films": getIds(specie.films),
//                        "created": specie.created,
//                        "edited": specie.edited
//                ]
//                writeEntry(out, properties)
//            }
//        }
//    }
//
//    def writeVehicles(vehicles) {
//        new File(outputDir, "vehicles.yml").withWriter { out ->
//            vehicles.each { vehicle ->
//                def properties = [
//                        "id": parseId(vehicle.url),
//                        "name": vehicle.name,
//                        "model": ignoreUnknown(vehicle.model),
//                        "manufacturer": ignoreUnknown(vehicle.manufacturer),
//                        "cost_in_credits": ignoreUnknown(vehicle.cost_in_credits),
//                        "length": ignoreUnknown(vehicle.length),
//                        "max_atmosphering_speed": ignoreUnknown(vehicle.max_atmosphering_speed),
//                        "crew": ignoreUnknown(vehicle.crew),
//                        "passengers": ignoreUnknown(vehicle.passengers),
//                        "cargo_capacity": ignoreUnknown(vehicle.cargo_capacity),
//                        "consumables": ignoreUnknown(vehicle.consumables),
//                        "vehicle_class": ignoreUnknown(vehicle.vehicle_class),
//                        "pilots": getIds(vehicle.pilots),
//                        "films": getIds(vehicle.films),
//                        "created": vehicle.created,
//                        "edited": vehicle.edited
//                ]
//                writeEntry(out, properties)
//            }
//        }
//    }
//
//    def writeStarships(starships) {
//        new File(outputDir, "starships.yml").withWriter { out ->
//            starships.each { starship ->
//                def properties = [
//                        "id": parseId(starship.url),
//                        "name": starship.name,
//                        "model": ignoreUnknown(starship.model),
//                        "manufacturer": ignoreUnknown(starship.manufacturer),
//                        "cost_in_credits": ignoreUnknown(starship.cost_in_credits),
//                        "length": ignoreUnknown(starship.length),
//                        "max_atmosphering_speed": ignoreUnknown(starship.max_atmosphering_speed),
//                        "crew": ignoreUnknown(starship.crew),
//                        "passengers": ignoreUnknown(starship.passengers),
//                        "cargo_capacity": ignoreUnknown(starship.cargo_capacity),
//                        "consumables": ignoreUnknown(starship.consumables),
//                        "hyperdrive_rating": ignoreUnknown(starship.hyperdrive_rating),
//                        "mglt": ignoreUnknown(starship.MGLT),
//                        "starship_class": ignoreUnknown(starship.starship_class),
//                        "pilots": getIds(starship.pilots),
//                        "films": getIds(starship.films),
//                        "created": starship.created,
//                        "edited": starship.edited
//                ]
//                writeEntry(out, properties)
//            }
//        }
//    }
//
//    def parseId(url) {
//        if (url.endsWith("/")) {
//            url = url.substring(0, url.length() - 1)
//        }
//        return url.substring(url.lastIndexOf("/") + 1)
//    }
//
//    def getIds(links) {
//        def ids = []
//        links.each { link ->
//            ids << parseId(link)
//        }
//        return ids
//    }
//
//    // sanitize
//    def ignoreUnknown(value) {
//        return (("unknown").equalsIgnoreCase(value) || "n/a".equalsIgnoreCase(value)) ? null : value
//    }
//
//    def writeEntry(out, properties) {
//        properties.eachWithIndex { property, i ->
//            def prefix = (i == 0) ? "-" : " "
//            def name = property.key
//            def value = property.value
//            if (value instanceof List) {
//                out.println "${prefix} ${name}:"
//                value.each {
//                    out.println "    - ${it}"
//                }
//            } else if ((value instanceof String || value instanceof GString) && value.startsWith("|- ")) {
//                def block = value.substring(3).replace("\r\n", "\r\n    ")
//                out.println "${prefix} ${name}: |-"
//                out.println "    ${block}"
//            } else {
//                out.println "${prefix} ${name}:" + (value ? " ${value}" : "")
//            }
//        }
//    }
}
