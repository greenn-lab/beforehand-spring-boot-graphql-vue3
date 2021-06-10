package beforehand.springboot.graphql.server.management.menu;

import beforehand.springboot.graphql.server.infrastructure.entity.EntityAuditor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Menu extends EntityAuditor {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  private String nameEn;
  private String uri;

  @Column(name = "ORD")
  private int order;

  @Column(name = "DSC", scale = 1000)
  private String description;

  private String icon;
  private String classes;
  private String badge;
  private boolean inactive;
  private boolean use;

  @OneToMany
  @JoinColumn(name = "UPPER_ID")
  @OrderBy("order asc")
  private List<Menu> children = new ArrayList<>();


  // -------------------------------------------------------
  // Mapper
  // -------------------------------------------------------
  @org.mapstruct.Mapper
  interface Mapper {

    Mapper mapped = Mappers.getMapper(Mapper.class);

    Menu by(Menu.Dto dao);

    Menu.Dto to(Menu menu);
  }


  // -------------------------------------------------------
  // DTO
  // -------------------------------------------------------
  @Getter
  @Setter
  static class Dto {

    private Long id;

    @NotEmpty
    private String name;

    private String nameEn;

    @URL
    private String uri;

    private int order = 0;
    private String description;
    private String icon;
    private String classes;
    private String badge;
    private boolean inactive = false;
    private boolean use = true;

  }

}
