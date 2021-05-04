 
 
public class User
{
	
	 /* 
	 * Properties
	 */

	private int id;
	private String name;
	private double balance; 

	/* 
CREATE TABLE UserDetails
  (id INTEGER NOT NULL,name VARCHAR(15), balance NUMBER, PRIMARY KEY(id));

  */
	/** 
	 * Setters and getters for the properties
	 */
	public String getName()
	{
		return name;
	}
	public void setName(String n) 
	{
		this.name = n;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public double getBalance() 
	{
		return balance;
	}
	public void setBalance(double bal)
	{
		this.balance = bal;
	}	 

}
