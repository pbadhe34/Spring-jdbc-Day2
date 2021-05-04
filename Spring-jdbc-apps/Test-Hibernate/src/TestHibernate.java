import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class TestHibernate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        //Build the configuration object to initualize db connection 
		Configuration cfg = new Configuration();

		//declarative configurations
		/*// cfg.setProperty( Environment.POOL_SIZE, "4" );
		cfg.setProperty(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver");
		cfg.setProperty(Environment.URL, "jdbc:oracle:thin:@localhost:1521:XE");
		
		cfg.setProperty(Environment.USER,"system");
		cfg.setProperty(Environment.PASS,"admin");
		
		
		//cfg.setProperty(Environment.TRANSACTION_STRATEGY,"JTA");
		cfg.setProperty(Environment.AUTOCOMMIT,"true");

		cfg.setProperty(Environment.DIALECT,
				"org.hibernate.dialect.Oracle10gDialect");
		cfg.setProperty(Environment.SHOW_SQL, "true");
		cfg.setProperty(Environment.FORMAT_SQL, "true");
		cfg.setProperty(Environment.HBM2DDL_AUTO, "create");
		cfg.addClass(Player.class);// Looks for Player.hbm.xml on classpath
*/
		//Global Factory to create database sessions
		cfg.configure();//load the configuration from hibernate.cfg.xml file
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		//session object to manage the database operations
		Session session = sessionFactory.openSession();

		Player master = new Player(); // define the player object

		master.setPosition("middle");
		master.setName("Vijay");
		master.setSalary(102344);
		master.setId(345);

		Transaction trnx = session.beginTransaction();
		//Save the player object to database
		Integer id = (Integer) session.save(master);
		session.flush();
		trnx.commit();
		System.out.println("The player object saved with id= " + id);

	}

}
