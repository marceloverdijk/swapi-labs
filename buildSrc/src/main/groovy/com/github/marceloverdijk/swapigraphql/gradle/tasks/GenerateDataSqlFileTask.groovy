package com.github.marceloverdijk.swapigraphql.gradle.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class GenerateDataSqlFileTask extends DefaultTask {

    String group = "SWAPI"
    String description = "Generates the data.sql file from the downloaded swapi.co SWAPI data."

    @TaskAction
    def generate() {

        println "Generating data.sql..."

        // TODO
    }
}
