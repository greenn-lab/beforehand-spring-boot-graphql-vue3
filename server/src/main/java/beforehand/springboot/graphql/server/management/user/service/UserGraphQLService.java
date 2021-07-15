package beforehand.springboot.graphql.server.management.user.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.github.greennlab.ddul.user.User;
import com.github.greennlab.ddul.user.repository.DDulUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class UserGraphQLService implements GraphQLQueryResolver, GraphQLMutationResolver {

  private final DDulUserRepository repository;

  @Value("${spring.data.web.pageable.default-page-size:10}")
  private int defaultPageSize;


  public Page<User> users(int page, int size) {
    final PageRequest pageable = PageRequest.of(page, size < 1 ? defaultPageSize : size);

    return repository.findAll(pageable);
  }

  //  @PreAuthorize("hasRole('MANAGER')")
  public User saveUser(@Validated User.Dto dto) {
    final User user = User.mapped.by(dto);

    return repository.save(user);
  }

}
