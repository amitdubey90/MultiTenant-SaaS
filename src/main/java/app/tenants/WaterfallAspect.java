package app.tenants;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class WaterfallAspect {

		@Pointcut("execution(* app.controllers..*AppController.hello(..))")
		 public void methodPointcut() {}
		
		@Around("methodPointcut()")
	    public Object hello(ProceedingJoinPoint pjp) throws Throwable {
			System.out.println("In aspect");
	        String name = pjp.getSignature().getName(); 
	        System.out.println(name);
	        return pjp.proceed();
	    }
}
