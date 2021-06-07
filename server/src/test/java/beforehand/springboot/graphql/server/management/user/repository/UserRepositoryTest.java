package beforehand.springboot.graphql.server.management.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import beforehand.springboot.graphql.server.infrastructure.config.SecurityConfiguration;
import beforehand.springboot.graphql.server.management.user.User;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

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
class UserRepositoryTest {

  @Autowired
  UserRepository repository;

  @Test
  void shouldGetUserWithUserAuthorities() {
    final Optional<User> user = repository.findById(-1L);

    assertThat(user.orElseThrow(RuntimeException::new)).isNotNull();
  }

}
