package com.github.marceloverdijk.swapigraphql.graphql;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateTimeScalarType extends GraphQLScalarType {

    private static Logger LOGGER = LoggerFactory.getLogger(DateTimeScalarType.class);

    public DateTimeScalarType() {
        super("DateTime", "A DateTime type", new Coercing<LocalDateTime, String>() {

            @Override
            public String serialize(final Object input) {
                // TODO
                return null;
            }

            @Override
            public LocalDateTime parseValue(final Object input) {
                // TODO
                return null;
            }

            @Override
            public LocalDateTime parseLiteral(final Object input) {
                // TODO
                return null;
            }
        });
    }
}
