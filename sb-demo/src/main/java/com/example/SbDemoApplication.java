package com.example;

import java.net.InetAddress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SbDemoApplication {
	
	@RequestMapping("/")
	public String msg(){
		
		return "App is up & running...";
	}
	
	@RequestMapping("/details")
	public String details(){
		
		String details = "No host details found.";
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			details = inetAddress.getHostAddress() + " : " + inetAddress.getHostName();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return details;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SbDemoApplication.class, args);
	}
}
