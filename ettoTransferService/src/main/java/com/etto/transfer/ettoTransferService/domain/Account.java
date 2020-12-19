package com.etto.transfer.ettoTransferService.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Account extends AbstractPersistable<Long> {

	private String name;
	private String isbn13;
	private String isbn10;
	
	public String toString() {
		return "User{"+
				"userId" + getId() +
				", username='" + name + '\'' +
				", isbn13='" + isbn13 + '\'' +
				", isbn10='" + isbn10 + '\'' +
				'}';
	}
}