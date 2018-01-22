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

package com.github.marceloverdijk.swapi.labs.jsonapi.web.util;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.Link;
import com.github.jasminb.jsonapi.Links;
import com.github.marceloverdijk.swapi.labs.jsonapi.web.resource.PageInfo;
import com.github.marceloverdijk.swapi.labs.jsonapi.web.resource.Resource;
import com.github.marceloverdijk.swapi.labs.jsonapi.web.resource.ResourceAssembler;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.github.jasminb.jsonapi.JSONAPISpecConstants.SELF;
import static com.github.marceloverdijk.swapi.labs.jsonapi.web.MetaFields.PAGE_INFO;

/**
 * {@link JSONAPIDocument} utils.
 *
 * @author Marcel Overdijk
 */
public class JSONAPIDocumentUtils {

    public static <T, D extends Resource> JSONAPIDocument<D> createDocument(final T data,
            final ResourceAssembler<T, D> assembler) {

        Objects.requireNonNull(data, "'data' must not be null");
        Objects.requireNonNull(assembler, "'assembler' must not be null");

        D resource = assembler.toResource(data);

        JSONAPIDocument<D> document = new JSONAPIDocument<>(resource);

        // TODO add self link?

        return document;
    }

    public static <T, D extends Resource> JSONAPIDocument<List<D>> createCollectionDocument(final List<T> data,
            final ResourceAssembler<T, D> assembler) {

        Objects.requireNonNull(data, "'data' must not be null");
        Objects.requireNonNull(assembler, "'assembler' must not be null");

        List<D> resources = toResources(data, assembler);

        JSONAPIDocument<List<D>> document = new JSONAPIDocument<>(resources);

        // TODO add self link?

        return document;
    }

    public static <T, D extends Resource> JSONAPIDocument<List<D>> createPagedDocument(final Page<T> page,
            final ResourceAssembler<T, D> assembler) {

        Objects.requireNonNull(page, "'page' must not be null");
        Objects.requireNonNull(assembler, "'assembler' must not be null");

        List<D> resources = toResources(page.getContent(), assembler);

        Map<String, Link> links = new HashMap<>();
        links.put(SELF, new Link(ServletUriComponentsBuilder.fromCurrentRequest().build().toString()));
        // TODO add next + prev links

        Map<String, Object> meta = new HashMap<>();
        meta.put(PAGE_INFO, new PageInfo(page));

        JSONAPIDocument<List<D>> document = new JSONAPIDocument<>(resources);
        document.setLinks(new Links(links));
        document.setMeta(meta);

        return document;
    }

    private static <T, D extends Resource> List<D> toResources(final Collection<T> data,
            final ResourceAssembler<T, D> assembler) {
        List<D> resources = new ArrayList<>(data.size());
        for (T element : data) {
            resources.add(assembler.toResource(element));
        }
        return resources;
    }
}
