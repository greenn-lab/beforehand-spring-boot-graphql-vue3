package beforehand.springboot.graphql.server.management.menu.service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.github.greennlab.ddul.menu.Menu;
import com.github.greennlab.ddul.menu.repository.DDulMenuRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class MenuGraphQLService implements GraphQLQueryResolver {

  private final DDulMenuRepository repository;


  public List<Menu> menus() {
    final Optional<Menu> root = repository.findById(0L);

    return root.orElseGet(Menu::new).getChildren();
  }

}
