package com.etto.transfer.ettoTransferService.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

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

	@NotNull(message = "ec2user cannot be null")
	private String ec2user = "etto";
	@NotNull(message = "host cannot be null")
	private String host = "13.114.173.103";
	@NotNull(message = "port cannot be null")
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