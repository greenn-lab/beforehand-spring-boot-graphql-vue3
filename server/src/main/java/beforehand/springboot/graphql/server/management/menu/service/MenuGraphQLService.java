package beforehand.springboot.graphql.server.management.menu.service;

import beforehand.springboot.graphql.server.management.menu.Menu;
import beforehand.springboot.graphql.server.management.menu.repository.MenuRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuGraphQLService implements GraphQLQueryResolver {

  private final MenuRepository repository;


  public List<Menu> menus() {
    final Optional<Menu> root = repository.findById(0L);
    return root.orElseGet(Menu::new).getChildren();
  }

}
