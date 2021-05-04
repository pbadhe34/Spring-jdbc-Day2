
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.server.Accounts;

public class DeclarativeAOPTest
{
   public static void main(String [] args)
   {
      // Read the configuration file
      ApplicationContext ctx  =  new FileSystemXmlApplicationContext("aop-config.xml");

      //Get an aop proxied bean
    
      //Accounts proxyObject = (Accounts) ctx.getBean("accountsBean"); //real accounts bean
      Accounts proxyObject = (Accounts) ctx.getBean("accountsTarget");//Proxied accoounts Bean
	  System.out.println("The object returned by aop container is \n"+ proxyObject.getClass().getName()+"\n");



      //Execute the public method of the bean (the testObject)
	  proxyObject.depositAmount(1300);
	  proxyObject.withdrawAmount(650, 45);
	  System.out.println("\n");

	  
      


   }
}
