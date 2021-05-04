package com.server;

import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


public class Accounts {

	
	 public void depositAmount(int amount) {
		 System.out.println("Accounts.depositAmount() for "+amount);
		 
	 }
	 public void withdrawAmount(int amount, int id) {
		 System.out.println("Accounts.withdrawAmount() for id= "+id +" amount = " +amount);
		 
	 }
}

