package com.etto.transfer.ettoTransferService.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.etto.transfer.ettoTransferService.domain.AwsUser;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

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

	private String key = "C:/Users/user/Desktop/Etto/ettoTransferService/src/main/resources/public.pem";
	
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
			log.info("Starting Connect =====> ");
			try
			{
				JSch jsch = new JSch();
				jsch.addIdentity(key);

				Session session = jsch.getSession(
				sessionID.get("user").toString(),
				sessionID.get("host").toString(),
					(int)sessionID.get("port"));
				log.info("session created");

				session.setConfig("StrictHostKeyChecking", "no");
				session.setConfig("GSSAPIAuthentication", "no");
				session.setServerAliveInterval(120 * 1000);
				session.setServerAliveCountMax(1000);
				session.setConfig("TCPKeepAlive","yes");

				session.connect();

				Channel channel = session.openChannel("shell");
		
				channel.setInputStream(System.in);
				channel.setOutputStream(System.out);
	
				channel.connect(3*1000);

				usrlist.add(sessionID);
			}
			catch(JSchException e)
			{
				e.printStackTrace();
			}
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
