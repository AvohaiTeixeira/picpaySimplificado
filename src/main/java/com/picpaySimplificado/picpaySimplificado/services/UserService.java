package com.picpaySimplificado.picpaySimplificado.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaySimplificado.picpaySimplificado.domain.user.User;
import com.picpaySimplificado.picpaySimplificado.domain.user.UserType;
import com.picpaySimplificado.picpaySimplificado.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public void validateTransaction(User sender, BigDecimal amount) throws Exception {
		//Verifica o tipo do Usuário
		if (sender.getUserType() == UserType.MERCHANT) {
			throw new Exception("Usuário do tipo Logista não está autorizado a realizar transação");
		}
		
		//Verifica se Usuário possui saldo suficiente
		if (sender.getBalance().compareTo(amount) < 0) {
			throw new Exception("Saldo suficiente!");
		}
	}
	
	public User findUserById (Long id) throws Exception {
		return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado!") );
	}
	
	//Cadastrar Usuário.
	public void saveUser (User user) { 
		this.repository.save(user);
	}
	
	
	
}
