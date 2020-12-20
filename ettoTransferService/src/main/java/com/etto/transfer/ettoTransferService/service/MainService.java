package com.etto.transfer.ettoTransferService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MainService implements ApplicationListener<ApplicationReadyEvent>{

	private Logger log = LoggerFactory.getLogger(MainService.class);

	@Autowired
	SSHConnect sshConnect;

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {

		log.info("Started MainService");
		sshConnect.getConInfo();
	}
	
	public void junitConnectTest(){
		log.info("Started Junit Test");
		sshConnect.getConInfo();
	}
	
}
