package beforehand.springboot.graphql.server.infrastructure.config;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.security.util.FieldUtils;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.LocaleContextMessageInterpolator;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;

@Configuration
@RequiredArgsConstructor
public class HibernateValidatorConfiguration {

  private final MessageSource messageSource;

  @PostConstruct
  public void setup() throws IllegalAccessException {
    final LocaleContextMessageInterpolator messageInterpolator =
        (LocaleContextMessageInterpolator) localValidatorFactoryBean().getMessageInterpolator();

    final Object targetInterpolatorValue =
        FieldUtils.getFieldValue(messageInterpolator, "targetInterpolator");

    FieldUtils.setProtectedFieldValue(
        "defaultResourceBundleLocator",
        targetInterpolatorValue,
        new FixupMessageSourceResourceBundleLocator(messageSource));
  }


  @Bean
  public LocalValidatorFactoryBean localValidatorFactoryBean() {
    return new LocalValidatorFactoryBean();
  }


  static class FixupMessageSourceResourceBundleLocator
      extends MessageSourceResourceBundleLocator {

    private final MessageSource messageSource;

    public FixupMessageSourceResourceBundleLocator(MessageSource messageSource) {
      super(messageSource);
      this.messageSource = messageSource;
    }

    @Override
    public ResourceBundle getResourceBundle(Locale locale) {
      return new FixupMessageSourceResourceBundle(messageSource, locale);
    }
  }

  static class FixupMessageSourceResourceBundle
      extends MessageSourceResourceBundle {

    public FixupMessageSourceResourceBundle(MessageSource source, Locale locale) {
      super(source, locale);
    }

    @Override
    protected Object handleGetObject(String key) {
      final Object value = super.handleGetObject(key);
      return key.equals(value) ? "{" + key + "}" : value;
    }
  }

}
