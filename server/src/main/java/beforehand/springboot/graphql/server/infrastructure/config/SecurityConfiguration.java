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

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Bean
  public AuditorAware<String> securityLinkageAuditorAware() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      return () -> Optional.of("(ghost)");
    }

    return () -> Optional.of(authentication.getPrincipal().toString());
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .inMemoryAuthentication()
        .withUser("reader").password("king123$")
        .roles(Grade.ADMIN.name(), Grade.MANAGER.name(), Grade.WORKER.name(), Grade.INTERN.name())
        .and()
        .withUser("tester").password("test123$")
        .roles(Grade.MANAGER.name()).and()
        .withUser("junior").password("baby123$")
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
