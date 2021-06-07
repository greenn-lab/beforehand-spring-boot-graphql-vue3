package beforehand.springboot.graphql.server.management.user;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Embeddable
@Getter
@Setter
public class UserNamed {

  @Length(min = 1, message = "is {min} size")
  private String name;

  private String familyName;
  private String nickname;

}
