 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.test.Account;



public class TestUser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Configuration cfg = new Configuration();	  

		  
		//read the configuration  from default config file on path	hibernate.cfg.xml
		 cfg.configure();		  
		 //Get the Global Factory to create sessions
		 SessionFactory sessionFactory = cfg.buildSessionFactory(); 
		 
		 
		//session object to manage the database operations
		 Session session = sessionFactory.openSession();
		 
         User obj = new User(); 
         obj.setname("dada");
         obj.setIncome(1123.78f);  
         obj.setNum( 26);
          
		 
		 Transaction trnx = session.beginTransaction();	
		//Save the player object to database
		// Integer id = (Integer) session.save(obj);
		 
		 //User userObj=  (User) session.load(User.class, 3) ;
         //The load throws exception if the record not exist in DB
         //System.out.println("The User name is  "+userObj.getname());
		 
		 
		 Account ac = new Account(12,23.67f);
		 Integer ac_id = (Integer) session.save(ac);
		 
		 
		 trnx.commit();
		 //System.out.println("The User object saved with "+id);
		 System.out.println("The Account object saved with "+ac_id);
		 

	   	 
	}

}
