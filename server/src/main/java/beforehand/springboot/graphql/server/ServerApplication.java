package beforehand.springboot.graphql.server;

import beforehand.springboot.graphql.server.user.User;
import beforehand.springboot.graphql.server.user.repository.UserRepository;
import java.util.Arrays;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServerApplication.class, args);
  }


  @Bean
  CommandLineRunner initialize(final UserRepository repository) {
    return args -> Arrays.asList(
        "red", "orange", "yello", "green", "blue", "indigo", "purple",
        "gold", "silver", "bronze", "cyan", "magenta", "black", "azure",
        "violet", "gray", "burgundy", "beige"
    ).forEach(color ->
        repository.save(new User(color, RandomUtils.nextInt(13, 150)))
    );
  }

}
