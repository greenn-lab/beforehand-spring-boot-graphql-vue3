package beforehand.springboot.graphql.server.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

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
        .headers()
        .frameOptions().sameOrigin()
        .and()

        .formLogin()
        .and()
        .authorizeRequests()
        .antMatchers("/*").permitAll()
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
