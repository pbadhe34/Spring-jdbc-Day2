 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;


public class TestHibernateConfig {

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
		 
         Player master = new Player();	//define the player object
		 
		 master.setPosition("kishor");
		 master.setName("oracle");
		 master.setSalary(456);
		 master.setId(115);
		 
		Transaction trnx = session.beginTransaction();	
		//Save the player object to database
		 Integer id = (Integer) session.save(master);
		trnx.commit();
		 System.out.println("The player object saved  "+id);

	   	 
	}

}
