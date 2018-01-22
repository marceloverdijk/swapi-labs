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

package com.github.marceloverdijk.swapi.labs.jsonapi.web.converter;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.exceptions.DocumentSerializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

import static com.github.marceloverdijk.swapi.labs.jsonapi.web.MediaTypes.APPLICATION_VND_API_JSON;

/**
 * A JSON API {@link HttpMessageConverter}
 *
 * @author Marcel Overdijk
 */
@Component
public class JSONAPIDocumentHttpMessageConverter extends AbstractGenericHttpMessageConverter<JSONAPIDocument> {

    private final ResourceConverter converter;

    @Autowired
    public JSONAPIDocumentHttpMessageConverter(final ResourceConverter converter) {
        super(APPLICATION_VND_API_JSON);
        this.converter = Objects.requireNonNull(converter, "'converter' must not be null");
    }

    @Override
    public JSONAPIDocument read(final Type type, final Class<?> contextClass, final HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        return readResolved(GenericTypeResolver.resolveType(type, contextClass), inputMessage);
    }

    @Override
    protected JSONAPIDocument readInternal(final Class<? extends JSONAPIDocument> clazz, final HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        return readResolved(clazz, inputMessage);
    }

    private JSONAPIDocument readResolved(Type resolvedType, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        throw new UnsupportedOperationException("See https://github.com/jasminb/jsonapi-converter/issues/131 for example implementation");
    }

    @Override
    protected void writeInternal(final JSONAPIDocument document, final Type type, final HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        try {
            Object data = document.get();
            byte[] message;
            if (data != null && Iterable.class.isAssignableFrom(data.getClass())) {
                message = converter.writeDocumentCollection((JSONAPIDocument<? extends Iterable<?>>) document);
            } else {
                message = converter.writeDocument(document);
            }
            outputMessage.getBody().write(message);
        } catch (DocumentSerializationException e) {
            throw new HttpMessageNotWritableException(e.getMessage(), e);
        }
    }
}
