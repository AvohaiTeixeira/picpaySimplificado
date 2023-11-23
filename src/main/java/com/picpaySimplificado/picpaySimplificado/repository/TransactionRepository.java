package com.picpaySimplificado.picpaySimplificado.repository;

import org.springframework.data.repository.CrudRepository;

import com.picpaySimplificado.picpaySimplificado.domain.transaction.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
