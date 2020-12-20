package com.etto.transfer.ettoTransferService.service;

import java.util.HashMap;

import com.etto.transfer.ettoTransferService.domain.AwsUser;
import com.jcraft.jsch.JSch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SSHConnect {

	@Autowired
	AwsUser awsUser;

	private Logger log = LoggerFactory.getLogger(SSHConnect.class);
	private HashMap<String, Object> sessionID;

	public HashMap<String, Object> getConInfo(){
		sessionID = new HashMap<String, Object>();
		log.info("setting Connect Info ----->");
			try {
				sessionID.put("user", awsUser.getEc2user());
				sessionID.put("host", awsUser.getHost());
				sessionID.put("port", awsUser.getPort());

				return sessionID;
			}
			catch(NullPointerException e)
			{
				log.info(e.getMessage());
				return sessionID;
			}
			finally
			{
				if(!sessionID.isEmpty())
				{
					for(String key:sessionID.keySet())
					{
						System.out.println(key + ":" + sessionID.get(key));
					}
				}
			}
	}
}
