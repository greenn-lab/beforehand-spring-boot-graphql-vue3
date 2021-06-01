package beforehand.springboot.graphql.server.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@RequiredArgsConstructor
public class JSR303WithMessageSourceConfiguration {

  private final MessageSource messageSource;


  @Bean
  public LocalValidatorFactoryBean localValidatorFactoryBean() {
    final LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
    bean.setValidationMessageSource(messageSource);

    return bean;
  }

}
