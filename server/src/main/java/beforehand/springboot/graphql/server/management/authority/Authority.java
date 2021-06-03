package beforehand.springboot.graphql.server.management.authority;

import beforehand.springboot.graphql.server.infrastructure.entity.EntityAuditor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Authority extends EntityAuditor {

  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true)
  private String role;

  @ManyToOne
  private Authority upper;

}
