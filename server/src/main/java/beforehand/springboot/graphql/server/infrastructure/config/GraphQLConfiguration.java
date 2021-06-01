package beforehand.springboot.graphql.server.infrastructure.config;

import beforehand.springboot.graphql.server.infrastructure.graphql.GraphQLMutationNotValidException;
import com.google.common.collect.ImmutableList;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import graphql.servlet.DefaultGraphQLErrorHandler;
import graphql.servlet.GenericGraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import java.lang.reflect.UndeclaredThrowableException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;

@Configuration
public class GraphQLConfiguration {

  @Bean
  public GraphQLErrorHandler errorHandler() {
    return new DefaultGraphQLErrorHandler() {
      @Override
      public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        for (GraphQLError error : errors) {
          if (error instanceof ExceptionWhileDataFetching) {
            final Throwable exception = ((ExceptionWhileDataFetching) error).getException();
            if (exception instanceof AccessDeniedException) {
              return ImmutableList.of(new GenericGraphQLError(exception.getMessage()));
            } else if (exception instanceof UndeclaredThrowableException) {
              final Throwable notValid =
                  ((UndeclaredThrowableException) exception).getUndeclaredThrowable();
              if (notValid instanceof GraphQLMutationNotValidException) {
                return ImmutableList.of((GraphQLMutationNotValidException) notValid);
              }
            }
          }
        }

        return super.processErrors(errors);
      }
    };
  }

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
