package beforehand.springboot.graphql.server.infrastructure.entity;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
@Getter
@Setter
public class EntityAuditor {

  @CreatedBy
  private String creator;

  @CreatedDate
  private LocalDateTime created;

  @LastModifiedBy
  private String modifier;

  @LastModifiedDate
  private LocalDateTime modified;

}
