package beforehand.springboot.graphql.server.infrastructure.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class JSRC303MessageInterpolatorFixupAspect {

  @Around("execution(protected Object org.springframework.context.support.MessageSourceResourceBundle.*(String))")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//    final String parameterName = (String) joinPoint.getArgs()[0];
//    final Object proceed = joinPoint.proceed();
//
//    return parameterName.equals("{" + proceed + "}")
//        ? parameterName
//        : proceed;

    return joinPoint.proceed();
  }

}
