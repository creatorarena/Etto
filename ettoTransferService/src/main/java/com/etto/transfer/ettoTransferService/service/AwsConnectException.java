package com.etto.transfer.ettoTransferService.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AwsConnectException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private Logger log = LoggerFactory.getLogger(AwsConnectException.class);

	private HashMap<String, Object> sessionID;
	private String message;

	public AwsConnectException(HashMap<String, Object> sessionID, String message){
		this.sessionID = sessionID;
		this.message = message;
		log.info("Connection is refused : " + sessionID + " " + message);
	}
}
