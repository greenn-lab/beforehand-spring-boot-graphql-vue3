package beforehand.springboot.graphql.server.infrastructure.config;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfiguration {

  @Bean
  public GraphQLScalarType dateTimeDefinition() {
    return new GraphQLScalarType("DateTime", "simple date-time type",
        new Coercing<LocalDateTime, String>() {
          @Override
          public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
            return dataFetcherResult.toString();
          }

          @Override
          public LocalDateTime parseValue(Object input) throws CoercingParseValueException {
            return LocalDateTime.parse(input.toString());
          }

          @Override
          public LocalDateTime parseLiteral(Object input) throws CoercingParseLiteralException {
            if (input instanceof StringValue) {
              final StringValue value = (StringValue) input;
              return LocalDateTime.parse(value.getValue());
            }

            throw new IllegalArgumentException();
          }
        }
    );
  }

  @Bean
  public GraphQLScalarType dateDefinition() {
    return new GraphQLScalarType("Date", "simple date type",
        new Coercing<LocalDate, String>() {
          @Override
          public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
            return dataFetcherResult.toString();
          }

          @Override
          public LocalDate parseValue(Object input) throws CoercingParseValueException {
            return LocalDate.parse(input.toString());
          }

          @Override
          public LocalDate parseLiteral(Object input) throws CoercingParseLiteralException {
            if (input instanceof StringValue) {
              final StringValue value = (StringValue) input;
              return LocalDate.parse(value.getValue());
            }

            throw new IllegalArgumentException();
          }
        }
    );
  }

}
