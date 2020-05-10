package com.jyotionjava.sdjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//import net.bull.javamelody.MonitoredWithSpring;

@SpringBootApplication
@EnableCaching
//@MonitoredWithSpring
public class SpringBootJpaSpringDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaSpringDataApplication.class, args);
	}
}
