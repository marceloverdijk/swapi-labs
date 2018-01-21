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

package com.github.marceloverdijk.swapi.labs.jsonapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.SerializationFeature;
import com.github.marceloverdijk.swapi.labs.jsonapi.web.resource.PersonResource;
import com.github.marceloverdijk.swapi.labs.jsonapi.web.resource.PlanetResource;
import com.github.marceloverdijk.swapi.labs.model.BaseModel;
import com.github.marceloverdijk.swapi.labs.repository.BaseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The main application class.
 *
 * @author Marcel Overdijk
 */
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = BaseRepository.class)
@EntityScan(basePackageClasses = BaseModel.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ResourceConverter resourceConverter(final ObjectMapper mapper) {
        // Do classpath scanning? https://github.com/jasminb/jsonapi-converter/issues/131
        Class<?>[] classes = new Class[] {
                PersonResource.class,
                PlanetResource.class
        };
        ResourceConverter converter = new ResourceConverter(mapper, classes);
        converter.enableSerializationOption(SerializationFeature.INCLUDE_RELATIONSHIP_ATTRIBUTES);
        return converter;
    }
}
