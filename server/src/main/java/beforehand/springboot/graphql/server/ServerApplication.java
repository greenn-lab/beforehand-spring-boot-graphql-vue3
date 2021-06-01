package beforehand.springboot.graphql.server;

import beforehand.springboot.graphql.server.todo.Todo;
import beforehand.springboot.graphql.server.todo.repository.TodoRepository;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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


  @Bean
  CommandLineRunner initialize(final TodoRepository repository) {
    return args -> Arrays.asList(
        "red", "orange", "yello", "green", "blue", "indigo", "purple",
        "gold", "silver", "bronze", "cyan", "magenta", "black", "azure",
        "violet", "gray", "burgundy", "beige"
    ).forEach(color -> repository.save(new Todo(color)));
  }

}
