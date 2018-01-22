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

package com.github.marceloverdijk.swapi.labs.jsonapi.web.controller;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.marceloverdijk.swapi.labs.jsonapi.web.resource.Resource;
import com.github.marceloverdijk.swapi.labs.jsonapi.web.resource.ResourceAssembler;
import com.github.marceloverdijk.swapi.labs.jsonapi.web.util.JSONAPIDocumentUtils;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * The base controller.
 *
 * @author Marcel Overdijk
 */
public abstract class BaseController {

    public <T, D extends Resource> JSONAPIDocument<D> createDocument(final T entity,
            final ResourceAssembler<T, D> assembler) {
        return JSONAPIDocumentUtils.createDocument(entity, assembler);
    }

    public <T, D extends Resource> JSONAPIDocument<List<D>> createCollectionDocument(final List<T> entities,
            final ResourceAssembler<T, D> assembler) {
        return JSONAPIDocumentUtils.createCollectionDocument(entities, assembler);
    }

    public <T, D extends Resource> JSONAPIDocument<List<D>> createPagedDocument(final Page<T> page,
            final ResourceAssembler<T, D> assembler) {
        return JSONAPIDocumentUtils.createPagedDocument(page, assembler);
    }
}
