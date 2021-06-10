package beforehand.springboot.graphql.server.management.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import beforehand.springboot.graphql.server.DataJpaTestConfiguration;
import beforehand.springboot.graphql.server.management.user.User;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(DataJpaTestConfiguration.class)
class UserRepositoryTest {

  @Autowired
  UserRepository repository;

  @Test
  void shouldGetUserWithUserAuthorities() {
    final Optional<User> user = repository.findById(-1L);

    assertThat(user.orElseThrow(RuntimeException::new)).isNotNull();
  }

}
