package com.etto.transfer.ettoTransferService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EttoTransferServiceApplication {

	private static Logger log = LoggerFactory.getLogger(EttoTransferServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EttoTransferServiceApplication.class, args);
		log.info("hello");
	}
	
}
