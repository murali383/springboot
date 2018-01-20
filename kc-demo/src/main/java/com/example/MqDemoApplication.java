package com.example;

import java.security.Principal;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableAutoConfiguration
@RestController
@Configuration
public class MqDemoApplication {
	
	@RequestMapping("/")
	public String hello()throws Exception{
		
		return "Welcome to keyclock.....";
	}
	
	@RequestMapping("/admin")
	public String admin(Principal principal){
		
		//KeycloakAuthenticationToken kAT = (KeycloakAuthenticationToken)principal;
		
		 
		return "Welcome to keyclock.....User: "+ principal.getName();
	}
	
	@RequestMapping("/user")
	public String user(Principal principal){
		return "Welcome to keyclock.....User: "+principal.getName();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MqDemoApplication.class, args);
	}
}
