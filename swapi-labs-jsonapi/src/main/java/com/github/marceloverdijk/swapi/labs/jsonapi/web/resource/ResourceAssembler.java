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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Interface for components that convert a domain type into a {@link Resource}.
 *
 * @author Marcel Overdijk
 */
public interface ResourceAssembler<T, D extends Resource> {

    /**
     * Converts the given entity into an {@link Resource}.
     *
     * @param entity the entity
     * @return the resource
     */
    D toResource(T entity);

    /**
     * Converts all given entities into {@link Resource}s.
     *
     * @see #toResource(Object)
     * @param entities the entities, must not be {@literal null}.
     * @return the resources
     */
    default List<D> toResources(Iterable<? extends T> entities) {
        Objects.requireNonNull(entities, "'entities' must not be null");
        List<D> result = new ArrayList<>();
        for (T entity : entities) {
            result.add(toResource(entity));
        }
        return result;
    }
}
