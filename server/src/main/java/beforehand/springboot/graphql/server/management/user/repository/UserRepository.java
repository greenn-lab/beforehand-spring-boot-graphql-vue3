package beforehand.springboot.graphql.server.management.user.repository;

import beforehand.springboot.graphql.server.infrastructure.repository.AFewRepository;
import beforehand.springboot.graphql.server.management.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository extends AFewRepository<User, Long> {

  Page<User> findAll(Pageable pageable);

  <E extends User> E saveAndFlush(E entity);

}
