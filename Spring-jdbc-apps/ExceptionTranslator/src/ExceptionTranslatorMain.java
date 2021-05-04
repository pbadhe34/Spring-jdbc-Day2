 

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;

import org.springframework.core.io.ClassPathResource;

import com.app.ExceptionTranslator;

public class ExceptionTranslatorMain {
	public static void main(String[] args) {
		BeanFactory factory = new XmlBeanFactory(new ClassPathResource(
				"ExceptionTranslator.xml"));

		ExceptionTranslator myBean = (ExceptionTranslator) factory
				.getBean("ETBean");

		myBean.updateRecords();
	}
}
