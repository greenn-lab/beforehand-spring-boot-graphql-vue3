package beforehand.springboot.graphql.server.management.authority.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import beforehand.springboot.graphql.server.infrastructure.config.SecurityConfiguration;
import beforehand.springboot.graphql.server.management.authority.Authority;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest(
    includeFilters = @Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = SecurityConfiguration.class
    ),
    properties = {
        "spring.jpa.hibernate.ddl-auto=none",
        "spring.jpa.properties.hibernate.format_sql=true"
    }
)
class AuthorityRepositoryTest {

  @Autowired
  AuthorityRepository repository;

  @Test
  void shouldGetHierarchyAuthorities() {
    final Optional<Authority> authority = repository.findById(-3L);
    assertThat(authority).isNotNull();
  }

}
