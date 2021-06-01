package beforehand.springboot.graphql.server.infrastructure.graphql;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

@JsonIncludeProperties({"message", "extensions"})
public class GraphQLMutationNotValidException
    extends MethodArgumentNotValidException
    implements GraphQLError {

  public GraphQLMutationNotValidException(MethodParameter parameter, BindingResult bindingResult) {
    super(parameter, bindingResult);
  }

  @Override
  public String getMessage() {
    return "Validation Error";
  }

  @Override
  public Map<String, Object> getExtensions() {
    return Collections.singletonMap("fields", super.getFieldErrors());
  }

  @Override
  public List<SourceLocation> getLocations() {
    return Collections.emptyList();
  }

  @Override
  public ErrorType getErrorType() {
    return null;
  }

}
