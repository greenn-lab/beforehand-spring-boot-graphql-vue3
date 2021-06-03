package beforehand.springboot.graphql.server.management.user;

import beforehand.springboot.graphql.server.infrastructure.entity.EntityAuditor;
import beforehand.springboot.graphql.server.management.authority.Authority;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class UserAuthority extends EntityAuditor {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToMany
  private List<User> users = new ArrayList<>();

  @ManyToMany
  private List<Authority> authorities = new ArrayList<>();

}
