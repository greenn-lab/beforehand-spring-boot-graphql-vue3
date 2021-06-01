package beforehand.springboot.graphql.server.todo.repository;

import beforehand.springboot.graphql.server.infrastructure.repository.AFewRepository;
import beforehand.springboot.graphql.server.todo.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoRepository extends AFewRepository<Todo, Long> {

  Page<Todo> findAll(Pageable pageable);

}
