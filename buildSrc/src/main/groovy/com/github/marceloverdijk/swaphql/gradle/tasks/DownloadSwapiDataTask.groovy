package com.github.marceloverdijk.swaphql.gradle.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class DownloadSwapiDataTask extends DefaultTask {

    String group = "SWAPI"
    String description = "Downloads all the swapi.co SWAPI data."

    @TaskAction
    def download() {

        println "Downloading data..."

        // TODO
    }
}
