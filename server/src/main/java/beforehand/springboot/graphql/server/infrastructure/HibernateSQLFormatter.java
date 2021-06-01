package beforehand.springboot.graphql.server.infrastructure;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.FormatStyle;

public class HibernateSQLFormatter implements MessageFormattingStrategy {

  @Override
  public String formatMessage(int connectionId, String now, long elapsed, String category,
      String prepared, String sql, String url) {
    return FormatStyle.BASIC.getFormatter().format(sql);
  }

}
