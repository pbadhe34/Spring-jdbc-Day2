package app.aspects;

 
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AdviceAccounts {

	@Before("execution(* com.server.Accounts.depositAmount(..))")
	public void beforeTargetMethod(JoinPoint joinPoint) {
		System.out.println("AdviceAccounts beforeRun() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		String targetClass = (String) joinPoint.getTarget().getClass().getName();
		Object args[] = joinPoint.getArgs();
		String kind = joinPoint.getKind();
		System.out.println("******"+kind+"************");
		String name = joinPoint.getSignature().getName();
		System.out.println("******"+"Method Name is "+name);
		System.out.println("\nMethod args are " +args[0]);
		System.out.println("\nTarget is " +targetClass);		 
		
	}
	
	@After("execution(* com.server.Accounts.withdrawAmount(..))")
	public void log(){
		System.out.println("AdviceAccounts after target class method execution");
	}
	 
	
	
	/*
	 * @AfterReturning(PointCut =
	 * "execution(* com.server.Accounts.depositAmount(..))", returning = "retVal")
	 * // @AfterReturning("execution(* com.server.Accounts.depositAmount(..))")
	 * public void getAmountForMe(JoinPoint joinPoint, Object result) {
	 * 
	 * System.out.println("getAmountForMe() after return..with results as "+result);
	 * }
	 */
	 
	 
	
	
	  @AfterThrowing( pointcut =
	  "execution(* com.server.Accounts.withdrawAmount(..))", throwing= "error")
	  public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
	  
	  System.out.println("logAfterThrowing() is running!");
	  System.out.println("hijacked after: " + joinPoint.getSignature().getName());
	  System.out.println("Exception : " + error); System.out.println("******");
	  
	  }
	 
	
	  @Around("execution(* com.server.Accounts.depositAmount(..))") 
	  public void
	  runningAround(ProceedingJoinPoint joinPoint) throws Throwable {
	  
	  System.out.println("The runningAround() is running!");
	  System.out.println("hijacked method in runningAround : " +
	  joinPoint.getSignature().getName());
	  System.out.println("hijacked arguments in runningAround : " +
	  Arrays.toString(joinPoint.getArgs()));
	  
	  System.out.println("Around before is running!"); joinPoint.proceed();
	  System.out.println("Around after is running!");
	  
	  System.out.println("******");
	  
	  }
	 


}
