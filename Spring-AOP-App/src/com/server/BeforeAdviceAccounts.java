package com.server;

 
import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class BeforeAdviceAccounts implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		 System.out.println("\n$$BeforeAdviceAccounts.before() Method Before "+method.getName());
		 System.out.println("BeforeAdviceAccounts.before() Parameetrs  are "+args[0]);
		 System.out.println("BeforeAdviceAccounts.before() target class is  "+target.getClass()
		 .getName());
		 String methodIntercepted = method.getName();
		 if(methodIntercepted=="withdrawAmount")
		 {
			  int amountToWithDraw = (int) args[0];
				
			 if(amountToWithDraw > 1000)
				 throw new IllegalArgumentException("The Larger Amount NOT allowed to wihDraw");
		 }
			
			
	}

}
