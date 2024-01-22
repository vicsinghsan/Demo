package com.vBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vBank.entity.User;


public interface IUserRepository extends JpaRepository<User, Long>{
	public User findByEmail(String email);
	
	boolean existsByAccountNumber(String accountNumber);
	
	public User findByAccountNumber(String accountNumber);

}
