package com.github.marceloverdijk.swapigraphql.gradle.tasks

import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class ExtractSwapiDataTask extends DefaultTask {

    String group = "SWAPI"
    String description = "Downloads and extracts all the swapi.co SWAPI data."

    String baseUrl = "https://swapi.co/api"
    File outputDir = project.file("src/data")

    JsonSlurper jsonSlurper = new JsonSlurper()

    @TaskAction
    def extract() {

        println "Retrieving data..."

        outputDir.mkdirs()

        def planets = getPagedData("planets")
        writePlanets(planets)

        def starships = getPagedData("starships")
        writeStarships(starships)

        println "Planets: " + planets.size()
        println "Starships: " + starships.size()

        println "Done"
    }

    def writePlanets(planets) {
        new File(outputDir, "planets.yml").withWriter { out ->
            planets.each { planet ->
                def properties = [
                        "id": parseId(planet.url),
                        "name": planet.name,
                        "diameter": ignoreUnknown(planet.diameter),
                        "rotation_period": ignoreUnknown(planet.rotation_period),
                        "orbital_period": ignoreUnknown(planet.orbital_period),
                        "gravity": ignoreUnknown(planet.gravity),
                        "population": ignoreUnknown(planet.population),
                        "climate": ignoreUnknown(planet.climate),
                        "terrain": ignoreUnknown(planet.terrain),
                        "surface_water": ignoreUnknown(planet.surface_water),
                        "residents": getIds(planet.residents),
                        "films": getIds(planet.films)
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
                        "model": starship.model,
                        "starship_class": starship.starship_class,
                        "manufacturer": ignoreUnknown(starship.manufacturer),
                        "cost_in_credits": ignoreUnknown(starship.cost_in_credits),
                        "length": ignoreUnknown(starship.length),
                        "crew": ignoreUnknown(starship.crew),
                        "passengers": ignoreUnknown(starship.passengers),
                        "max_atmosphering_speed": ignoreUnknown(starship.max_atmosphering_speed),
                        "hyperdrive_rating": ignoreUnknown(starship.hyperdrive_rating),
                        "mglt": ignoreUnknown(starship.MGLT),
                        "cargo_capacity": ignoreUnknown(starship.cargo_capacity),
                        "consumables": ignoreUnknown(starship.consumables),
                        "films": getIds(starship.films),
                        "pilots": getIds(starship.pilots)
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
