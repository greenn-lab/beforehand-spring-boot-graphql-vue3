package beforehand.springboot.graphql.server;

import java.util.Optional;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

@TestConfiguration
public class DataJpaTestConfiguration {

  @Bean
  AuditorAware<String> securityLinkageAuditorAware() {
    return () -> Optional.of("data-jpa-test");
  }

}
