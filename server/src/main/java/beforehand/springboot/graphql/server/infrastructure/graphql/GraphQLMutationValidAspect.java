package beforehand.springboot.graphql.server.infrastructure.graphql;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Component
@Aspect
@RequiredArgsConstructor
public class GraphQLMutationValidAspect {

  private final SpringValidatorAdapter validator;


  @Pointcut("@target(org.springframework.stereotype.Service) || @target(org.springframework.stereotype.Component)")
  public void componentClass() {
    // use to pointcut
  }

  @Pointcut("execution(* *(.., @org.springframework.validation.annotation.Validated (*), ..))")
  public void hasValidatedArgumentMethods() {
    // use to pointcut
  }

  @Before("componentClass() && hasValidatedArgumentMethods()")
  public void before(JoinPoint joinPoint) throws MethodArgumentNotValidException {
    final Signature signature = joinPoint.getSignature();
    final Method method = ((MethodSignature) signature).getMethod();
    final Parameter[] parameters = method.getParameters();

    for (int i = 0; i < parameters.length; i++) {
      final MethodParameter methodParameter = new MethodParameter(method, i);
      if (methodParameter.hasParameterAnnotation(Validated.class)) {
        final Object target = joinPoint.getArgs()[i];
        final String name = Optional
            .ofNullable(methodParameter.getParameterName())
            .orElse(parameters[i].getName());

        final BindingResult errors = new BeanPropertyBindingResult(target, name);
        validator.validate(target, errors);

        if (errors.hasErrors()) {
          throw new GraphQLMutationNotValidException(methodParameter, errors);
        }
      }
    }
  }
}
