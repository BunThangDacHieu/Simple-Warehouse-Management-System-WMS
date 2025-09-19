package com.example.backend.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.backend.model.Transaction;
import com.example.backend.repository.TransactionRepository;
import com.example.backend.util.ObjectValidator;

@Service
public class TransactionService {

    @Autowired
    private ObjectValidator objectValidator;

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /*-------------------------------------CRUD cơ bản------------------------------------- */
    //Lấy danh sách của toàn bộ hàng hóa
    public ResponseEntity<List<Transaction>> getAllTransaction() {
        try {
            List<Transaction> transactions = transactionRepository.findAll();
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Tạo
    public Transaction createTransaction(Transaction transaction) {
        Map<String, String> errors = objectValidator.getRequestAndSummitErrors(transaction);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }
        return transactionRepository.save(transaction);
    }

    //Tìm dựa trên id
    public Optional<Transaction> findTransactionbyId(int id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("Transaction id must be greater than 0");
            }
            return transactionRepository.findById(id);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
    //Xóa

    public void deleteTransaction(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Transaction id must be greater than 0");
        }
        transactionRepository.deleteById(id);
    }
    /*-------------------------------------Logic nghiệp vụ------------------------------------- */
}
