package com.etto.transfer.ettoTransferService.service;

import java.util.HashMap;

import com.etto.transfer.ettoTransferService.domain.AwsUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("asyncTask")
public class NewTargetServer {
	
	@Autowired
	SSHConnect sshConnect;

	Logger log = LoggerFactory.getLogger(NewTargetServer.class);
	
	@Async("executorTest")
	public void executor(AwsUser awsUser) {
		log.info("success get Thread and started");
		HashMap<String, Object> sessionID = new HashMap<String, Object>();
		if(sshConnect.getConInfo(sessionID, awsUser) != null);
		{
			log.info("SSH Session Information is created");
			sshConnect.connectTo(sessionID);
		}
	}
}
