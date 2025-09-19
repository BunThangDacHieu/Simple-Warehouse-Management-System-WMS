package com.example.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Transaction;
import com.example.backend.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /*--------------------------------CRUD cơ bản -------------------------------- */
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransaction() {
        try {
            List<Transaction> transactions = transactionService.getAllTransaction();
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Transaction>> getTransactionById(@Valid @RequestParam int id) {
        try {
            Optional<Transaction> transactions = transactionService.findTransactionbyId(id);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<Transaction> createtreansaction(@Valid @RequestBody Transaction transaction) {
        try {
            Transaction newTransaction = transactionService.createTransaction(transaction);
            return ResponseEntity.ok(newTransaction);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // @PutMapping("/transaction/{id}")
    // public ResponseEntity<Transaction> updateTransaction(@PathVariable int id, @RequestBody Transaction transaction) {
    //     try {
    //         transaction.setId(id);
    //         Transaction updatedTransaction = transactionService.updateTransaction(transaction);
    //         return ResponseEntity.ok(updatedTransaction);
    //     } catch (Exception e) {
    //         throw new RuntimeException(e);
    //     }
    // }
    //Xóa 
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable int id) {
        try {
            transactionService.deleteTransaction(id);
            return ResponseEntity.ok("Delete successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /*------------------------------------Logic nâng cao -------------------------------*/
}
