package beforehand.springboot.graphql.server.infrastructure.config;

import java.util.Locale;
import java.util.ResourceBundle;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;

@Configuration
@RequiredArgsConstructor
public class JSR303WithMessageSourceConfiguration {

  private final MessageSource messageSource;


  @Bean
  public LocalValidatorFactoryBean localValidatorFactoryBean() {
    final LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
    bean.setMessageInterpolator(new ResourceBundleMessageInterpolator(
        new FixupMessageSourceResourceBundleLocator(messageSource)
    ));
//    bean.setValidationMessageSource(messageSource);
//    bean.setMessageInterpolator(new MessageInterpolator() {
//      @Override
//      public String interpolate(String s, Context context) {
//        return ;
//      }
//
//      @Override
//      public String interpolate(String s, Context context, Locale locale) {
//        return s;
//      }
//    });
    return bean;
  }

  static class FixupMessageSourceResourceBundleLocator
      extends MessageSourceResourceBundleLocator {

    private final MessageSource messageSource;

    public FixupMessageSourceResourceBundleLocator(MessageSource messageSource) {
      super(messageSource);
      this.messageSource = messageSource;
    }

    @NotNull
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
