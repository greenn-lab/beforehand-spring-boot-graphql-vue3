package beforehand.springboot.graphql.server.management.user;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetail extends User implements UserDetails {

  private static final long serialVersionUID = -8073138182242641310L;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getFlatAuthorities().collect(Collectors.toList());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !isLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return getPasswordExpired().isAfter(LocalDate.now());
  }

  @Override
  public boolean isEnabled() {
    return isAccountNonExpired() && isCredentialsNonExpired();
  }

}
