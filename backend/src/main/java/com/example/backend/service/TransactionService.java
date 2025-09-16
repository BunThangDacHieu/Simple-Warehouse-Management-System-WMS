package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend.model.Transaction;
import com.example.backend.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /*-------------------------------------CRUD cơ bản------------------------------------- */
    //Lấy danh sách của toàn bộ hàng hóa
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    //Tạo
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    //Tìm dựa trên id
    public Optional<Transaction> findTransactionbyId(int id) {
        return transactionRepository.findById(id);
    }

    //Xóa
    public void deleteTransaction(int id) {
        transactionRepository.deleteById(id);
    }
    /*-------------------------------------Logic nghiệp vụ------------------------------------- */
}
