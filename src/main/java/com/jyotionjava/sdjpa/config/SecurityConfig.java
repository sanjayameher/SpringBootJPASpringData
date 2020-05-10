//http://www.springboottutorial.com/securing-rest-services-with-spring-boot-starter-security
//http://www.sedooe.com/2016/04/rest-authentication-using-spring-security-and-spring-session/
//http://www.tothenew.com/blog/fortifying-your-rest-api-using-spring-security/
//https://spring.io/guides/tutorials/bookmarks/
package com.jyotionjava.sdjpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;

/*
As of Spring Security 4.0, @EnableWebMvcSecurity is deprecated. 
The replacement is @EnableWebSecurity which will determine adding the Spring MVC features based upon the classpath.
To enable Spring Security integration with Spring MVC add the @EnableWebSecurity annotation to a @Configuration class
*/
 
 
@Configuration
@EnableWebSecurity
@PropertySource("classpath:config.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${admin.username}")
	private String adminUserName;
	
	@Value("${admin.password}")
	private String adminPassword;
	
	@Value("${user.username}")
	private String userUserName;
	
	@Value("${user.password}")
	private String userPassword;
	
	// Authentication : User --> Roles
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication()
		.withUser(userUserName).password(userPassword).roles("USER").and()
		.withUser(adminUserName).password(adminPassword).roles("ADMIN");
	}

	// Authorization : Role -> Access
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
		.antMatchers("/","/swagger-resources" ).permitAll()
		.antMatchers("/booking/read" ,"/booking/readall").permitAll()
		.antMatchers("/booking/getSpecificBooking/**").hasRole("USER")
		//.antMatchers("/booking/create/**", "/booking/update/**" ,"/booking/delete/**").hasRole("ADMIN")
		.antMatchers("/booking/create/**", "/booking/update/**" ,"/booking/delete/**").permitAll()
		.and().csrf().disable().headers().frameOptions().disable();
	}
		 

}
  