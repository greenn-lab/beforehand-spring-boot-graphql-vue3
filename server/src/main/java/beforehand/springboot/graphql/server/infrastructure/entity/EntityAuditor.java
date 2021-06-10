package beforehand.springboot.graphql.server.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import javax.persistence.Column;
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
@JsonIgnoreProperties({"creator", "created", "modifier", "modified"})
public class EntityAuditor {

  private boolean deleted;

  @CreatedBy
  @Column(updatable = false)
  private String creator;

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime created;

  @LastModifiedBy
  private String modifier;

  @LastModifiedDate
  private LocalDateTime modified;

}
