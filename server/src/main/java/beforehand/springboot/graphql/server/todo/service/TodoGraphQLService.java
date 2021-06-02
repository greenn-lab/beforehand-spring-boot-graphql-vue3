package beforehand.springboot.graphql.server.todo.service;

import beforehand.springboot.graphql.server.todo.Todo;
import beforehand.springboot.graphql.server.todo.repository.TodoRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
public class TodoGraphQLService implements GraphQLQueryResolver, GraphQLMutationResolver {

  private final TodoRepository repository;

  @Value("${spring.data.web.pageable.default-page-size:10}")
  private int defaultPageSize;


  public Page<Todo> todos(int page, int size) {
    return repository.findAll(PageRequest.of(page, size < 1 ? defaultPageSize : size));
  }

  @PreAuthorize("hasRole('MANAGER')")
  public Todo addTodos(@Validated Todo.Dto dto) {
    final Todo todo = Todo.Mapper.mapped.by(dto);
    return repository.save(todo);
  }

}
