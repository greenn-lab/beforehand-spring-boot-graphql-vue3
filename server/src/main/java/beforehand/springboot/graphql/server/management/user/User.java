package beforehand.springboot.graphql.server.management.user;

import beforehand.springboot.graphql.server.infrastructure.entity.EntityAuditor;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class User extends EntityAuditor implements UserDetails {

  @Id
  @GeneratedValue
  private Long id;

  private String email;
  private String username;
  private String password;
  private LocalDate passwordExpired;
  private boolean locked;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return passwordExpired.isAfter(LocalDate.now());
  }

  @Override
  public boolean isEnabled() {
    return isAccountNonExpired() && isCredentialsNonExpired();
  }

  // --------------------------------------------------
  // Mapper
  // --------------------------------------------------
  @org.mapstruct.Mapper
  @SuppressWarnings("unused")
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

    @Size(min = 1, max = 3)
    private String title;
    private LocalDate completed;

  }

}
