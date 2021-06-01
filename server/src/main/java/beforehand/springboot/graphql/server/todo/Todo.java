package beforehand.springboot.graphql.server.todo;

import beforehand.springboot.graphql.server.infrastructure.entity.EntityAuditor;
import java.time.LocalDate;
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

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Todo extends EntityAuditor {

  @Id
  @GeneratedValue
  private Long id;

  private String title;
  private LocalDate completed;


  public Todo(String title) {
    this.title = title;
  }


  @org.mapstruct.Mapper
  @SuppressWarnings("unused")
  public interface Mapper {

    Mapper mapped = Mappers.getMapper(Mapper.class);

    Todo by(Todo.Dto dto);

    Todo.Dto to(Todo user);

  }

  @Getter
  @Setter
  public static class Dto {

    private Long id;

    @Size(min = 1, max = 3)
    private String title;
    private LocalDate completed;

  }

}
