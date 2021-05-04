package com.server;

public class Billing {

	  private String customerName;
	  private double amount;
	  
	 public void payAmount(int amount, String customerName) {
		 System.out.println("Billing.payAmount() for "+customerName +" with "+amount);
		 
	 }
	 public void manageAccount(int id) {
		 System.out.println("Billing.manageAccount() for id= "+id);
		 
	 }
}
