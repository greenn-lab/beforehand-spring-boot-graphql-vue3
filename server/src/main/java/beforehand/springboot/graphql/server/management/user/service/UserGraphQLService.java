package beforehand.springboot.graphql.server.management.user.service;

import beforehand.springboot.graphql.server.management.user.User;
import beforehand.springboot.graphql.server.management.user.repository.UserRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
public class UserGraphQLService implements GraphQLQueryResolver, GraphQLMutationResolver {

  private final UserRepository repository;

  @Value("${spring.data.web.pageable.default-page-size:10}")
  private int defaultPageSize;


  public Page<User> users(int page, int size) {
    return repository.findAll(PageRequest.of(page, size < 1 ? defaultPageSize : size));
  }

//  @PreAuthorize("hasRole('MANAGER')")
  public User saveUser(@Validated User.Dto dto) {
    final User user = User.Mapper.mapped.by(dto);
    return repository.save(user);
  }

}
