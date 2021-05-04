

import org.app.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.server.Accounts;
import app.aspects.AdviceAccounts;
 
public class AOPAnnotationsTestWithoutXML {

	public static void main(String args[]) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		Accounts obj = (Accounts) ctx.getBean(Accounts.class);
		obj.depositAmount(1000);
		obj.withdrawAmount(1200, 101);
	}
}
