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

import org.springframework.http.MediaType;

/**
 * Constants for media types.
 *
 * @author Marcel Overdijk
 */
public class MediaTypes {

    /**
     * A String equivalent of {@link #APPLICATION_VND_API_JSON}.
     */
    public final static String APPLICATION_VND_API_JSON_VALUE = "application/vnd.api+json";

    /**
     * Public constant media type for {@code application/vnd.api+json}.
     */
    public final static MediaType APPLICATION_VND_API_JSON = MediaType.valueOf(APPLICATION_VND_API_JSON_VALUE);

    private MediaTypes() {
    }
}
