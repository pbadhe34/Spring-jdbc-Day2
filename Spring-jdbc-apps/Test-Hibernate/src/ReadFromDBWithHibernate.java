 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class ReadFromDBWithHibernate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Configuration cfg = new Configuration();
		  
		  //Otherwise make declarative configurations
		 cfg.configure();//read the configuration  from default file on path	
		 
		 SessionFactory sessionFactory = cfg.buildSessionFactory(); 
		 Session session = sessionFactory.openSession();
		 
		 Player player = (Player)session.get(Player.class, 780);
		 //The get returns null object if  the record not exist in DB
		 
        // Player player=  (Player) session.load(Player.class, 44) ;
         //The load throws exception if the record not exist in DB
         System.out.println("The player name is  "+player.getName());
		 
         player.setName("Mohan");		 
         //No need to explicitly save the modified object in DB
         //The session object updates it automatically in dB
         session.beginTransaction().commit();//no need to call sesion.save/update
         session.close();

	   	 
	}

}
