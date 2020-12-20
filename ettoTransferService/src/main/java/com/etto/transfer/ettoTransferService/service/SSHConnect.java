package com.etto.transfer.ettoTransferService.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.etto.transfer.ettoTransferService.domain.AwsUser;
import com.jcraft.jsch.JSch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Component
public class SSHConnect {

	@Autowired
	AwsUser awsUser;

	private Logger log = LoggerFactory.getLogger(SSHConnect.class);

	private ArrayList<HashMap<String, Object>> usrlist = new ArrayList<HashMap<String, Object>>();

	private String user;
	private String host;
	private String port;

	private String key;
	
	//User Information for ssh Connect
	public HashMap<String, Object> getConInfo(HashMap<String, Object> sessionID){
		log.info("setting Connect Info ----->");
			try
			{
				sessionID.put("user", awsUser.getEc2user());
				sessionID.put("host", awsUser.getHost());
				sessionID.put("port", awsUser.getPort());
				return sessionID;
			}
			catch(NullPointerException e)
			{
				e.printStackTrace();
				return null;
			}
			finally
			{
				if(!sessionID.isEmpty())
				{
					StringBuffer sBuffer = new StringBuffer();
					for(String key:sessionID.keySet())
					{
						sBuffer.append(key + ":" + sessionID.get(key));
					}
					log.info(sBuffer.toString());
				}
			}
	}

	@ExceptionHandler(AwsConnectException.class)
	public int connectTo(HashMap<String, Object> sessionID) {
		if(usrinfoCheck(sessionID) == 1) {
			log.info("Starting Connect");
			usrlist.add(sessionID);
		}
		return 1;
	}

	//Check User Information
	public int usrinfoCheck(HashMap<String, Object> sessionID){

		if(sessionID.containsValue(null))
		{
			new AwsConnectException(sessionID, "no Information");
			return 0;
		}
		ArrayList<String> usrInfoList = new ArrayList<String>();
		for(String str : sessionID.keySet())
		{
			usrInfoList.add(str);
			usrInfoList.add(sessionID.get(str).toString());
		}
		if(!usrInfoList.isEmpty())
		{
			StringBuffer sBuffer = new StringBuffer().append("\"");
			for(Object obj:usrInfoList)
			{
				if(usrInfoList.indexOf(obj)%2 == 0)
				{
					sBuffer.append("{" + obj.toString() + ": ");
				}
				else
				{
					sBuffer.append(obj.toString() + "}, ");
				}
			}
			sBuffer.append("\"");
			log.info("SSH Session information : " + sBuffer.toString());
		}
		log.info("sshUserInfoCheck is successed");

		return 1;
	}

	public ArrayList<HashMap<String, Object>> getUsrlist() {
		return usrlist;
	}

}
