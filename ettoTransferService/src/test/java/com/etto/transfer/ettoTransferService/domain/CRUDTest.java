package com.etto.transfer.ettoTransferService.domain;

import org.junit.Test;
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

	@Test
	public void testSave(){
		account = new Account();
		account.setName("name");
		account.setIsbn10("0123456789");
		account.setIsbn13("0123456789012");
		repository.save(account);
		repository.flush();

	}

	@Test
	public void testSave2(){
		awsUser = new AwsUser();
		awsUser.setEc2user("etto");
	}

}
