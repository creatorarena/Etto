package com.etto.transfer.ettoTransferService.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.etto.transfer.ettoTransferService.domain.AwsUser;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Component
public class SSHConnect {

	private Logger log = LoggerFactory.getLogger(SSHConnect.class);

	private ArrayList<HashMap<String, Object>> usrlist = new ArrayList<HashMap<String, Object>>();

	private String sshKey = "C:/Users/user/Desktop/Etto/ettoTransferService/src/main/resources/public.pem";
	
	// User Information for ssh Connect
	// @Async("executorTest")
	public HashMap<String, Object> getConInfo(HashMap<String, Object> sessionID, AwsUser awsUser){
		log.info("setting Connect Info ----->");

		// setting session information from AwsUser
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

	// by Park, connect to ssh with session information
	@ExceptionHandler(AwsConnectException.class)
	public int connectTo(HashMap<String, Object> sessionID) {
		if(usrinfoCheck(sessionID) == 1) {
			log.info("Starting Connect =====> ");
			try
			{
				JSch jsch = new JSch();
				jsch.addIdentity(sshKey);
				
				// inject session information
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

				// create channel, usable [shell, exec, sftp ・・・]
				ChannelExec channel = (ChannelExec)session.openChannel("exec");
				
				InputStream in = channel.getInputStream();

				channel.setCommand("ps -ef | grep tomcat");
				channel.connect();

				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String line;
				int index = 0;
				while ((line = reader.readLine()) != null)
				{
					log.info(++index + ":" + line);
				}

				channel.disconnect();
				session.disconnect();

				usrlist.add(sessionID);

			}
			catch(JSchException e)
			{
				e.printStackTrace();
			}
			catch(Exception e)
			{
				System.err.println("Error" + e);
			}
		}
		return 1;
	}

	// by Park, Check User Information 
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
