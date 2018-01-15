package com.github.marceloverdijk.swapigraphql.gradle.tasks

import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class ExtractSwapiDataTask extends DefaultTask {

    String group = "SWAPI"
    String description = "Downloads and extracts all the swapi.co SWAPI data."

    String baseUrl = "https://swapi.co/api"
    File outputDir = project.file("src/data/swapi")

    JsonSlurper jsonSlurper = new JsonSlurper()

    String unknownPlanetId

    @TaskAction
    def extract() {

        println "Retrieving data..."

        outputDir.deleteDir()
        outputDir.mkdirs()

        def people = getPagedData("people")
        def planets = getPagedData("planets")
        def films = getPagedData("films")
        def species = getPagedData("species")
        def vehicles = getPagedData("vehicles")
        def starships = getPagedData("starships")

        // Find and remove the "unknown" planet.
        unknownPlanetId = parseId(planets.find { ("unknown").equalsIgnoreCase(it.name) }.url)
        planets.removeAll { parseId(it.url) == unknownPlanetId }
        println "Removed 'unknown' planet (id=${unknownPlanetId})"

        writePeople(people)
        writePlanets(planets)
        writeFilms(films)
        writeSpecies(species)
        writeVehicles(vehicles)
        writeStarships(starships)

        println "People: " + people.size()
        println "Planets: " + planets.size()
        println "Films: " + films.size()
        println "Species: " + species.size()
        println "Vehicles: " + vehicles.size()
        println "Starships: " + starships.size()

        println "Done"
    }

    def writePeople(people) {
        new File(outputDir, "people.yml").withWriter { out ->
            people.each { person ->
                def homeworld = person.homeworld ? parseId(person.homeworld) : null
                if (homeworld == unknownPlanetId) {
                    // Remove reference to the "unknown" planet and instead use "null".
                    homeworld = null
                }
                def properties = [
                        "id": parseId(person.url),
                        "name": person.name,
                        "height": ignoreUnknown(person.height),
                        "mass": ignoreUnknown(person.mass),
                        "hair_color": ignoreUnknown(person.hair_color),
                        "skin_color": ignoreUnknown(person.skin_color),
                        "eye_color": ignoreUnknown(person.eye_color),
                        "birth_year": ignoreUnknown(person.birth_year),
                        "gender": ignoreUnknown(person.gender),
                        "homeworld": homeworld,
                        "films": getIds(person.films),
                        "species": getIds(person.species),
                        "vehicles": getIds(person.vehicles),
                        "starships": getIds(person.starships),
                        "created": person.created,
                        "edited": person.edited
                ]
                writeEntry(out, properties)
            }
        }
    }

    def writePlanets(planets) {
        new File(outputDir, "planets.yml").withWriter { out ->
            planets.each { planet ->
                def properties = [
                        "id": parseId(planet.url),
                        "name": planet.name,
                        "rotation_period": ignoreUnknown(planet.rotation_period),
                        "orbital_period": ignoreUnknown(planet.orbital_period),
                        "diameter": ignoreUnknown(planet.diameter),
                        "climate": ignoreUnknown(planet.climate),
                        "gravity": ignoreUnknown(planet.gravity),
                        "terrain": ignoreUnknown(planet.terrain),
                        "surface_water": ignoreUnknown(planet.surface_water),
                        "population": ignoreUnknown(planet.population),
                        "residents": getIds(planet.residents),
                        "films": getIds(planet.films),
                        "created": planet.created,
                        "edited": planet.edited
                ]
                writeEntry(out, properties)
            }
        }
    }

    def writeFilms(films) {
        new File(outputDir, "films.yml").withWriter { out ->
            films.each { film ->
                def properties = [
                        "id": parseId(film.url),
                        "title": film.title,
                        "episode_id": film.episode_id,
                        "opening_crawl": film.opening_crawl ? "|- ${film.opening_crawl}" : null,
                        "director": ignoreUnknown(film.director),
                        "producer": ignoreUnknown(film.producer),
                        "release_date": ignoreUnknown(film.release_date),
                        "characters": getIds(film.characters),
                        "planets": getIds(film.planets),
                        "starships": getIds(film.starships),
                        "vehicles": getIds(film.vehicles),
                        "species": getIds(film.species),
                        "created": film.created,
                        "edited": film.edited
                ]
                writeEntry(out, properties)
            }
        }
    }

    def writeSpecies(species) {
        new File(outputDir, "species.yml").withWriter { out ->
            species.each { specie ->
                def properties = [
                        "id": parseId(specie.url),
                        "name": specie.name,
                        "classification": ignoreUnknown(specie.classification),
                        "designation": ignoreUnknown(specie.designation),
                        "average_height": ignoreUnknown(specie.average_height),
                        "skin_colors": ignoreUnknown(specie.skin_colors),
                        "hair_colors": ignoreUnknown(specie.hair_colors),
                        "eye_colors": ignoreUnknown(specie.eye_colors),
                        "average_lifespan": ignoreUnknown(specie.average_lifespan),
                        "homeworld": specie.homeworld ? parseId(specie.homeworld) : null,
                        "language": ignoreUnknown(specie.language),
                        "people": getIds(specie.people),
                        "films": getIds(specie.films),
                        "created": specie.created,
                        "edited": specie.edited
                ]
                writeEntry(out, properties)
            }
        }
    }

    def writeVehicles(vehicles) {
        new File(outputDir, "vehicles.yml").withWriter { out ->
            vehicles.each { vehicle ->
                def properties = [
                        "id": parseId(vehicle.url),
                        "name": vehicle.name,
                        "model": ignoreUnknown(vehicle.model),
                        "manufacturer": ignoreUnknown(vehicle.manufacturer),
                        "cost_in_credits": ignoreUnknown(vehicle.cost_in_credits),
                        "length": ignoreUnknown(vehicle.length),
                        "max_atmosphering_speed": ignoreUnknown(vehicle.max_atmosphering_speed),
                        "crew": ignoreUnknown(vehicle.crew),
                        "passengers": ignoreUnknown(vehicle.passengers),
                        "cargo_capacity": ignoreUnknown(vehicle.cargo_capacity),
                        "consumables": ignoreUnknown(vehicle.consumables),
                        "vehicle_class": ignoreUnknown(vehicle.vehicle_class),
                        "pilots": getIds(vehicle.pilots),
                        "films": getIds(vehicle.films),
                        "created": vehicle.created,
                        "edited": vehicle.edited
                ]
                writeEntry(out, properties)
            }
        }
    }

    def writeStarships(starships) {
        new File(outputDir, "starships.yml").withWriter { out ->
            starships.each { starship ->
                def properties = [
                        "id": parseId(starship.url),
                        "name": starship.name,
                        "model": ignoreUnknown(starship.model),
                        "manufacturer": ignoreUnknown(starship.manufacturer),
                        "cost_in_credits": ignoreUnknown(starship.cost_in_credits),
                        "length": ignoreUnknown(starship.length),
                        "max_atmosphering_speed": ignoreUnknown(starship.max_atmosphering_speed),
                        "crew": ignoreUnknown(starship.crew),
                        "passengers": ignoreUnknown(starship.passengers),
                        "cargo_capacity": ignoreUnknown(starship.cargo_capacity),
                        "consumables": ignoreUnknown(starship.consumables),
                        "hyperdrive_rating": ignoreUnknown(starship.hyperdrive_rating),
                        "mglt": ignoreUnknown(starship.MGLT),
                        "starship_class": ignoreUnknown(starship.starship_class),
                        "pilots": getIds(starship.pilots),
                        "films": getIds(starship.films),
                        "created": starship.created,
                        "edited": starship.edited
                ]
                writeEntry(out, properties)
            }
        }
    }

    def parseId(url) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1)
        }
        return url.substring(url.lastIndexOf("/") + 1)
    }

    def getIds(links) {
        def ids = []
        links.each { link ->
            ids << parseId(link)
        }
        return ids
    }

    def ignoreUnknown(value) {
        return (("unknown").equalsIgnoreCase(value) || "n/a".equalsIgnoreCase(value)) ? null : value
    }

    def writeEntry(out, properties) {
        properties.eachWithIndex { property, i ->
            def prefix = (i == 0) ? "-" : " "
            def name = property.key
            def value = property.value
            if (value instanceof List) {
                out.println "${prefix} ${name}:"
                value.each {
                    out.println "    - ${it}"
                }
            } else if ((value instanceof String || value instanceof GString) && value.startsWith("|- ")) {
                def block = value.substring(3).replace("\r\n", "\r\n    ")
                out.println "${prefix} ${name}: |-"
                out.println "    ${block}"
            } else {
                out.println "${prefix} ${name}:" + (value ? " ${value}" : "")
            }
        }
    }

    def getPagedData(path) {
        def results = []
        def hasNext = true
        def page = 1
        while (hasNext) {
            println "Retrieving ${path} page ${page}..."
            def data = getData("${path}?page=${page}")
            results += data.results
            hasNext = data.next != null
            page++
        }
        return results
    }

    def getData(path) {
        def url = "${baseUrl}/${path}".toURL()
        def data = url.getText(
                connectTimeout: 10 * 1000,
                readTimeout: 10 * 1000,
                useCaches: false,
                requestProperties: [
                        "User-Agent": "swapi-graphql",
                        "Accept"    : "application/json"])
        return jsonSlurper.parseText(data)
    }
}
