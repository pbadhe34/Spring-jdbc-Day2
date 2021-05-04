import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import java.util.*;

class TestUserDAO 
{
	private UserDao dao;

	public void setDao(UserDao udao)
	{  
	   System.out.println("Setting the UserDao "+udao);
       dao=udao;
	}
	public UserDao getDao()
	{  
	   System.out.println("Returning the UserDao "+dao);
       return dao;
	}

	public static void main(String[] args) 
	{
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
        "applicationContext.xml");        
        
		
		TestUserDAO test = (TestUserDAO)ctx.getBean("Target");
		int id = 113;

		User user = new User();
		//user.setId(id);
		user.setName("Vijay");
		user.setBalance(2345.87);

		test.dao.add(user);

		/*

		User user= test.dao.getUser(id);

		System.out.println("User Name and Balance for requested id is  "
		+user.getName()+"  : "+user.getBalance());
		double bal = 24.00;
		List list = test.dao.getHighBalanceUsers(bal);
		System.out.println("Number of users are "+list.size());
		for(int i =0;i < list.size();i++)
		{
			User u= (User)list.get(i);
		    System.out.println("User   "+u.getName()+"   id is   "+u.getId());
		}

		*/


	}
}
