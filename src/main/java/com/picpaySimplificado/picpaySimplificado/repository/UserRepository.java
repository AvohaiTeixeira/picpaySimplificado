package com.picpaySimplificado.picpaySimplificado.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.picpaySimplificado.picpaySimplificado.domain.user.User;

public interface UserRepository  extends CrudRepository<User, Long> {

	Optional<User> findUserByDocument(String document);
	
	Optional<User> findUserById(Long id);
}
