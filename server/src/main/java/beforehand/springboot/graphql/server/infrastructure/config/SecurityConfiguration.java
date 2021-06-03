package beforehand.springboot.graphql.server.infrastructure.config;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Bean
  AuditorAware<String> securityLinkageAuditorAware() {
    return () -> {
      final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication == null) {
        return Optional.of("{ghost}");
      }

      final Object principal = authentication.getPrincipal();
      if (principal instanceof User) {
        return Optional.of(((User) principal).getUsername());
      }

      return Optional.of(principal.toString());
    };
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .inMemoryAuthentication()
        .withUser("leader").password("{noop}king123$")
        .roles(Grade.ADMIN.name(), Grade.MANAGER.name(), Grade.WORKER.name(), Grade.INTERN.name())
        .and()
        .withUser("tester").password("{noop}test123$")
        .roles(Grade.MANAGER.name()).and()
        .withUser("junior").password("{noop}baby123$")
        .roles(Grade.WORKER.name(), Grade.INTERN.name())
    ;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .httpBasic().disable()
        .formLogin()
        .and()
        .authorizeRequests()
        .anyRequest().permitAll()
        .and()
        .anonymous().principal("Anonymous").authorities("GUEST")
    ;
  }


  enum Grade {
    ADMIN,
    MANAGER,
    WORKER,
    INTERN
  }
}
