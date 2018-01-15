package com.github.marceloverdijk.swaphql.gradle.tasks

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

        println "Retrieving planets..."
        def planets = getPagedData("planets")
        writePlanets(planets)

        println "Planets: " + planets.size()

        println "Done"
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
                        "films": getIds(planet.films)
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
        // TODO what about N/A
        return value == "unknown" ? null : value
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
