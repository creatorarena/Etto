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
	private String ec2user;
	@NotNull(message = "host cannot be null")
	private String host;
	@NotNull(message = "port cannot be null")
	private int port;

	private String key;

}