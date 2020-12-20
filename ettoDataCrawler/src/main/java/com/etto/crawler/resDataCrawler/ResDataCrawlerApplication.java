package com.etto.crawler.resDataCrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class ResDataCrawlerApplication {
	private static Logger log = LoggerFactory.getLogger(ResDataCrawlerApplication.class);
	private static String keyname = "C:/Users/user/Desktop/Etto/ettoDataCrawler/src/main/resources/private.pem";
	private static String publicDNS = "13.114.173.103";
	private static String ec2user = "etto";
	private static int port = 22;

	public static void main(String[] args) {
		SpringApplication.run(ResDataCrawlerApplication.class, args);

		log.info("real real real hard");
		log.info("file:");
		String privateKey = keyname;
		
		Session session = null;
		Channel channel = null;
		
		try 
		{
			JSch jsch = new JSch();
			String user = ec2user;
			String host = publicDNS;

			log.info("===> Connecting to " + host);			
			jsch.addIdentity(privateKey);			
			log.info("identity added");

			session = jsch.getSession(user, host, port);
			log.info("session created.");

			session.setConfig("StrictHostKeyChecking", "no");
			session.setConfig("GSSAPIAuthentication", "no");
			session.setServerAliveInterval(120 * 1000);
			session.setServerAliveCountMax(1000);
			session.setConfig("TCPKeepAlive","yes");

			session.connect();
			
			channel = session.openChannel("shell");
		
			channel.setInputStream(System.in);
			channel.setOutputStream(System.out);

			channel.connect(3*1000);
		} 
		catch (JSchException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(channel != null)
			{
				channel.disconnect();
			}
			if(session != null)
			{
				session.disconnect();
			}
		}
	}

}
