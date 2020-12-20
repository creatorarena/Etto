package com.etto.transfer.ettoTransferService.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Component
public class AwsUser extends AbstractPersistable<Long> {

	private String ec2user;
	private String host;
	private int port = 22;
	private String key;

	public String toString() {
		return "awsUser{"+
				"userId" + getId() +
				", ec2user='" + ec2user + '\'' +
				", host='" + host + '\'' +
				", port='" + port + '\'' +
				", key='" + key + '\'' +
				'}';
	}
}