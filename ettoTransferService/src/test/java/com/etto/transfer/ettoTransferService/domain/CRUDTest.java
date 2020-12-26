package com.etto.transfer.ettoTransferService.domain;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CRUDTest {
	
	@Autowired
	AccountRepository repository;

	@Autowired
	AwsUserRepository awsRepository;

	private Account account;
	private AwsUser awsUser;
	private AwsUser awsUser2;

	@Test
	public void testSave(){
		account = new Account();
		account.setName("name");
		account.setIsbn10("0123456789");
		account.setIsbn13("0123456789012");
		repository.save(account);
		repository.flush();
		repository.findByNameLike("na");
		
	}

	@Test
	public void testSave2(){
		awsUser = new AwsUser();
		awsUser.setEc2user("etto");
		awsUser.setHost("172.22.12.190");
		awsUser.setPort(22);

		awsRepository.save(awsUser);

		awsUser2 = new AwsUser();
		awsUser2.setEc2user("itzy2");
		awsUser2.setHost("172.22.12.190");
		awsUser2.setPort(22);

		awsRepository.save(awsUser2);
		int index = 0;
		
		Assertions.assertEquals(awsUser.getEc2user(), awsRepository.findByEc2user("etto").getEc2user());
		Assertions.assertEquals(awsUser2.getEc2user(), awsRepository.findByEc2user("itzy2").getEc2user());

		for(AwsUser key : awsRepository.findAll())
		{
			System.out.println("index : "+ index + " ID : " + key.getId() + " ecu2ser : " + key.getEc2user() + " host : " + key.getHost() + " port : " + key.getPort() + " end/");
			index++;
		}
	}

}
