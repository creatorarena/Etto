package com.etto.transfer.ettoTransferService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class MainService {

	private Logger log = LoggerFactory.getLogger(MainService.class);

	@Autowired
	SSHConnect sshConnect;

	@EventListener(ApplicationReadyEvent.class)
	public void mainService(){
		log.info("Started MainService");
		log.info("closing apl");
	}
	
	public void junitConnectTest(){

		log.info("Started Junit Test");
		// sessionID = new HashMap<String, Object>();
	}
	
}
