package com.etto.transfer.ettoTransferService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EttoTransferServiceApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(EttoTransferServiceApplication.class, args);
		System.out.println("hello aws");
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(EttoTransferServiceApplication.class);
	}
}
