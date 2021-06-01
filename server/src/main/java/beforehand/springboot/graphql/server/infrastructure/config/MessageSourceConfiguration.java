package beforehand.springboot.graphql.server.infrastructure.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class MessageSourceConfiguration {

  @Bean
  public MessageSource messageSource(
      @Value("${spring.messages.cache-duration}") int cacheDuration
  ) throws IOException {
    final ReloadableResourceBundleMessageSource
        source = new ReloadableResourceBundleMessageSource();

    source.setBasenames(findOutBasename());
    source.setCacheSeconds(cacheDuration);
    source.setUseCodeAsDefaultMessage(true);

    return source;
  }

  private String[] findOutBasename() throws IOException {
    final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    final Resource[] resources = resolver.getResources("classpath:i18n/**/*.xml");
    final List<String> sources = new ArrayList<>();

    String group = "*";
    for (Resource res : resources) {
      final String filename = res.getFilename();
      assert filename != null;

      if (!filename.startsWith(group)) {
        group = filename.substring(0, filename.lastIndexOf("."));
        sources.add("classpath:i18n/" + group);
      }
    }

    return sources.toArray(new String[0]);
  }

}
