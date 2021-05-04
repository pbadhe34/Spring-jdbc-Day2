 
 
import java.util.*;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

 
 
/**
 * Hibernate implementation of the UserDao.
 */

public class HibernateUserDao extends HibernateDaoSupport
implements UserDao
{
    public void add(User obj)
	{
          HibernateTemplate template = getHibernateTemplate();
          template.save(obj);
	}

    //UserDao implementations
     public User getUser(int id)
	 {
         HibernateTemplate template = getHibernateTemplate();
         User user = (User)template.get(User.class, new Integer(id));
		 return user;
	 }

    public List getHighBalanceUsers(double bal)
	{
     HibernateTemplate template = getHibernateTemplate();
	 //Fire HQL Query and NOT sql query
     List result = template.find("from User where balance > ?",new Double(bal));
     return result; 

	}
	 


     
}
