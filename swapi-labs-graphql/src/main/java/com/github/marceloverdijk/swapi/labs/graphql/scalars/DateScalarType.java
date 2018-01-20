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

package com.github.marceloverdijk.swapi.labs.graphql.scalars;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * The date scalar.
 *
 * @author Marcel Overdijk
 */
@Component
public class DateScalarType extends GraphQLScalarType {

    private static Logger LOGGER = LoggerFactory.getLogger(DateScalarType.class);

    public DateScalarType() {
        super("Date", "A Date type", new Coercing<LocalDate, String>() {

            @Override
            public String serialize(final Object input) {
                // TODO
                return null;
            }

            @Override
            public LocalDate parseValue(final Object input) {
                // TODO
                return null;
            }

            @Override
            public LocalDate parseLiteral(final Object input) {
                // TODO
                return null;
            }
        });
    }
}
