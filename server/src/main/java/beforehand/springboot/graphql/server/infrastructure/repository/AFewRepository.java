package beforehand.springboot.graphql.server.infrastructure.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface AFewRepository<ENTITY, ID> extends Repository<ENTITY, ID> {

  ENTITY findById(ID id);

  ENTITY save(ENTITY user);

}
