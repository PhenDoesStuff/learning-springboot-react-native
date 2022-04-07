package com.montague.serverproductmanagement.repository;

import com.montague.serverproductmanagement.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
