package beforehand.springboot.graphql.server.management.authority.repository;

import static org.assertj.core.api.Assertions.assertThat;

import beforehand.springboot.graphql.server.DataJpaTestConfiguration;
import beforehand.springboot.graphql.server.infrastructure.config.SecurityConfiguration;
import beforehand.springboot.graphql.server.management.authority.Authority;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(DataJpaTestConfiguration.class)
class AuthorityRepositoryTest {

  @Autowired
  AuthorityRepository repository;

  @Test
  void shouldGetHierarchyAuthorities() {
    final Optional<Authority> authority = repository.findById(-2L);

    assertThat(authority)
        .isNotNull()
        .isPresent();

    final List<Authority> authorities = authority.get().getAllAsFlat().collect(Collectors.toList());
    assertThat(authorities.size()).isGreaterThanOrEqualTo(4);

  }


}
