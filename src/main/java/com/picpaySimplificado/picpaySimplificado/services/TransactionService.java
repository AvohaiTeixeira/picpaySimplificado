package com.picpaySimplificado.picpaySimplificado.services;

import java.math.BigDecimal;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaySimplificado.picpaySimplificado.domain.user.User;
import com.picpaySimplificado.picpaySimplificado.dtos.TransactionDTO;
import com.picpaySimplificado.picpaySimplificado.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository tRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestTemplate template;

	public void createTransaction (TransactionDTO transaction) throws Exception {
		User sender = this.userService.findUserById(transaction.senderId());
		User receiver = this.userService.findUserById(transaction.receiverId());
		
		//Caso o método abaixo não gere nem uma exceção, pode continuar com o fluxo.
		this.userService.validateTransaction(sender, transaction.amount());
		
		
		Boolean authorize = this.authorizeTransaction(sender, transaction.amount());
		if (!authorize) {
			throw new Exception("Transação não autorizada!");
		}
		
		
		
	}
	
	public Boolean authorizeTransaction (User sender, BigDecimal amount) {
		 ResponseEntity<Map> authorizationResponse = template.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
	
		 if (authorizationResponse.getStatusCode() == (HttpStatus.OK)
				/* && authorizationResponse.getBody().get("message") == "Autorizado" */ ) {
			 return true; 
		 } else {
			 return false;
		 }
	}
	
}
