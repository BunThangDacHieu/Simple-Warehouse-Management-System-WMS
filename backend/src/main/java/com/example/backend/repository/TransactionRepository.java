package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.bussinessObject.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
