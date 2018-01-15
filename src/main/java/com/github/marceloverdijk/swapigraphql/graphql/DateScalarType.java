package com.github.marceloverdijk.swapigraphql.graphql;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
