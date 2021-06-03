package beforehand.springboot.graphql.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaAuditing(auditorAwareRef = "securityLinkageAuditorAware")
public class ServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServerApplication.class, args);
  }

}
