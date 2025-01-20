package com.example.articleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
@Slf4j
public class ArticleappApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext con =  SpringApplication.run(ArticleappApplication.class, args);
		// SomePojo obj = con.getBean("somePojo", SomePojo.class);
		// log.info("remoteAddr : {}",obj.getRemoteAddr());	
		// log.info("securitysss : {}",obj.getSecurityUserName());
	}

}
