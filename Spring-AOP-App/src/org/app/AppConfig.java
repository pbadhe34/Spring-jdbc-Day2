package org.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.server.Accounts;

 
@ComponentScan(value = "app.aspects")
@EnableAspectJAutoProxy
public class AppConfig {
    
	@Bean
	Accounts getAccoountsBean() {
        return new Accounts();
    }
}