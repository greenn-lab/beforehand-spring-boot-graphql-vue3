package beforehand.springboot.graphql.server.infrastructure.config;

import beforehand.springboot.graphql.server.infrastructure.graphql.GraphQLMutationNotValidException;
import com.coxautodev.graphql.tools.ObjectMapperConfigurer;
import com.coxautodev.graphql.tools.SchemaParserOptions;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.greennlab.ddul.MessageConfiguration.ExceptionMessageSource;
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
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GraphQLConfiguration {

  private final ExceptionMessageSource exceptionMessageSource;

  @Bean
  SchemaParserOptions schemaParserOptions() {
    final ObjectMapperConfigurer objectMapperConfigurer =
        (mapper, context) -> mapper.registerModule(new JavaTimeModule());

    return SchemaParserOptions.newOptions()
        .objectMapperConfigurer(objectMapperConfigurer)
        .build();
  }

  @Bean
  GraphQLErrorHandler errorHandler() {
    return new DefaultGraphQLErrorHandler() {
      @Override
      public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        final List<GraphQLError> handledErrors = new ArrayList<>(errors.size());

        for (GraphQLError error : errors) {
          if (error instanceof ExceptionWhileDataFetching) {
            final Throwable exception = ((ExceptionWhileDataFetching) error).getException();

            if (!validateExceptionHandled(exception, handledErrors)) {
              final String message = exceptionMessageSource
                  .get(exception.getClass())
                  .orElse(exception.getMessage());

              handledErrors.add(new GenericGraphQLError(message));
            }
          } else {
            handledErrors.add(error);
          }
        }

        return handledErrors;
      }
    };
  }

  private boolean validateExceptionHandled(Throwable exception, List<GraphQLError> handledErrors) {
    if (exception instanceof UndeclaredThrowableException) {
      final Throwable notValidException =
          ((UndeclaredThrowableException) exception).getUndeclaredThrowable();

      if (notValidException instanceof GraphQLMutationNotValidException) {
        return handledErrors.add((GraphQLMutationNotValidException) notValidException);
      }
    }

    return false;
  }

  @Bean
  GraphQLScalarType scalarDateTime() {
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
  GraphQLScalarType scalarDate() {
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
