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

package com.github.marceloverdijk.swapi.labs.jsonapi.web;

/**
 * Constants for paths.
 *
 * @author Marcel Overdijk
 */
public class Paths {

    public static final String PATH_PERSON_RESOURCES = "/persons";
    public static final String PATH_PERSON_RESOURCE_BY_ID = "/persons/{planet-id}";
    public static final String PATH_PLANET_RESOURCES = "/planets";
    public static final String PATH_PLANET_RESOURCE_BY_ID = "/planets/{planet-id}";

    private Paths() {
    }
}
