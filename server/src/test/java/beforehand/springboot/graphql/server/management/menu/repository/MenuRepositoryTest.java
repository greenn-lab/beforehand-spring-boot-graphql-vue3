package beforehand.springboot.graphql.server.management.menu.repository;

import static org.assertj.core.api.Assertions.assertThat;

import beforehand.springboot.graphql.server.DataJpaTestConfiguration;
import beforehand.springboot.graphql.server.management.menu.Menu;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


@DataJpaTest
@Import(DataJpaTestConfiguration.class)
class MenuRepositoryTest {

  @Autowired
  MenuRepository repository;

  @Test
  void shouldGetMenuAllChildren() {
    final Optional<Menu> adminMenus = repository.findById(0L);

    assertThat(adminMenus).isPresent();
  }

}
