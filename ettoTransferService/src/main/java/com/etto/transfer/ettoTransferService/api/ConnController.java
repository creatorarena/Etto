package com.etto.transfer.ettoTransferService.api;

import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;

import com.etto.transfer.ettoTransferService.config.Ansyconfig;
import com.etto.transfer.ettoTransferService.domain.AwsUser;
import com.etto.transfer.ettoTransferService.domain.AwsUserRepository;
import com.etto.transfer.ettoTransferService.service.NewTargetServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class ConnController {
	
	private Logger log = LoggerFactory.getLogger(ConnController.class);

	@Resource(name = "asyncTask")
	private NewTargetServer asyncTask;

	@Resource(name = "ansyConfig")
	private Ansyconfig config;

	@Resource(name = "awsUser")
	private AwsUser awsUser;

	@Resource(name = "awsUserRepository")
	private AwsUserRepository awsUserRepository;

	@GetMapping(value="/api")
	@ResponseStatus(value = HttpStatus.OK)
	public String getMethodName() {
		return "ok";
	}
	
	// @GetMapping(value="/test")
	// @ResponseStatus(value = HttpStatus.OK)
	// public void doTask() throws Exception{
	// 	asyncTask.executor("TEST");
	// }

	// @PostMapping(value="/test")
	// public void putAwsUser(@RequestParam("ec2user") String ec2user, @RequestParam("host") String host, @RequestParam("port") String port){
	// 	try
	// 	{
	// 		awsUser = new AwsUser();
	// 		awsUser.setEc2user(ec2user);
	// 		awsUser.setHost(host);
	// 		awsUser.setPort(Integer.parseInt(port));
	// 	}
	// 	catch(Exception e)
	// 	{
	// 		return "Cannot create "
	// 	}
	// }

	@PutMapping(value = "/awsuser")
	public AwsUser createAwsUser(@RequestBody AwsUser parmawsUser){
		String msg = "from Admin >> " + "ec2user : " + parmawsUser.getEc2user() + ", host : " + parmawsUser.getHost() + ", port : " + parmawsUser.getPort();
		log.info("createAwsUser " + msg);

		awsUser = new AwsUser();
		awsUser.setEc2user(parmawsUser.getEc2user());
		awsUser.setHost(parmawsUser.getHost());
		awsUser.setPort(parmawsUser.getPort());

		awsUserRepository.save(awsUser);
		asyncTask.executor(awsUser);

		return awsUser;
	}

	@GetMapping(value = "/awsuser")
	public AwsUser getAwsUser(@RequestParam Long id){
		awsUser = awsUserRepository.findById(id).orElseThrow();
		return awsUser;
	}

}
