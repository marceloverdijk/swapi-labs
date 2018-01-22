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

package com.github.marceloverdijk.swapi.labs.jsonapi.web.resource;

/**
 * Interface for components that convert a data object type into a {@link Resource}.
 *
 * @author Marcel Overdijk
 */
public interface ResourceAssembler<T, D extends Resource> {

    /**
     * Converts the given data object into an {@link Resource}.
     *
     * @param data the data object
     * @return the resource
     */
    D toResource(T data);
}
