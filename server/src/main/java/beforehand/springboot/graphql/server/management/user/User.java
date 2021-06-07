package beforehand.springboot.graphql.server.management.user;

import beforehand.springboot.graphql.server.infrastructure.entity.EntityAuditor;
import beforehand.springboot.graphql.server.management.authority.Authority;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class User extends EntityAuditor {

  private static final long serialVersionUID = -7382145646927293876L;

  @Id
  @GeneratedValue
  private Long id;

  @Embedded
  private UserNamed named;

  private String email;
  private String username;
  private String password;
  private LocalDate passwordExpired;
  private boolean locked;

  @OneToMany(mappedBy = "user")
  private List<UserAuthority> userAuthorities = new ArrayList<>();

  public Stream<Authority> getFlatAuthorities() {
    return getUserAuthorities().stream()
        .map(UserAuthority::getAuthority)
        .flatMap(Authority::getAllAsFlat);
  }

  // --------------------------------------------------
  // Mapper
  // --------------------------------------------------
  @org.mapstruct.Mapper
  public interface Mapper {

    Mapper mapped = Mappers.getMapper(Mapper.class);

    User by(User.Dto dto);

    User.Dto to(User user);

  }

  // --------------------------------------------------
  // DTO
  // --------------------------------------------------
  @Getter
  @Setter
  public static class Dto {

    private Long id;

    @Valid
    private UserNamed named;

    @Email
    private String email;

    @NotEmpty
    private String username;

    @NotBlank
    private String password;

    private LocalDate passwordExpired;

    private boolean locked;

  }

}
