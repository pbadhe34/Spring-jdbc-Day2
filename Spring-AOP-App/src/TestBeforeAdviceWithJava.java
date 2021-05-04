import org.springframework.aop.framework.ProxyFactory;

import com.server.Accounts;
import com.server.BeforeAdviceAccounts;
import com.server.Billing;

public class TestBeforeAdviceWithJava {

	public static void main(String[] args) {

		// create the target object
		Accounts bean = new Accounts();

		// create the advice
		BeforeAdviceAccounts advice = new BeforeAdviceAccounts();

		// get the proxy for AOP container
		ProxyFactory factory = new ProxyFactory();
		factory.setTarget(bean);

		factory.addAdvice(advice);

		Accounts proxy = (Accounts) factory.getProxy();

		System.out.println("The proxy class is " + proxy.getClass().getName());

		proxy.depositAmount(1000);
		proxy.withdrawAmount(999, 101);

	}

}
