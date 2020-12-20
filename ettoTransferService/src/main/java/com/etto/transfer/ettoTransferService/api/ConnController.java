package com.etto.transfer.ettoTransferService.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
public class ConnController {
	
	@GetMapping(value="/api")
	@ResponseStatus(value = HttpStatus.OK)
	public String getMethodName() {
		return "ok";
	}
	
	
	
}
