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
 * The base resource assembler.
 *
 * @author Marcel Overdijk
 */
public abstract class BaseResourceAssembler<T, D extends Resource> implements ResourceAssembler<T, D> {

    /**
     * Converts the given entity into an {@link Resource}.
     *
     * @param entity the entity
     * @return the resource
     */
    public D toResource(T entity) {
        if (entity == null) {
            return null;
        }
        return toResourceInternal(entity);
    }

    protected abstract D toResourceInternal(T entity);
}
