package beforehand.springboot.graphql.server.infrastructure.repository;

import java.util.Optional;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface AFewRepository<ENTITY, ID> extends Repository<ENTITY, ID> {

  Optional<ENTITY> findById(ID id);

  ENTITY save(ENTITY user);

}
