package com.etto.transfer.ettoTransferService.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwsUserRepository extends JpaRepository<AwsUser, Long>{
	
	List<AwsUser> findByec2userLike(String ec2user);
}
