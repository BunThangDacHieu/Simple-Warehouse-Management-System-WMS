package com.example.backend.controller;

import java.util.List;
import java.util.Optional;

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

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /*--------------------------------CRUD cơ bản -------------------------------- */
    @GetMapping("/")
    public List<Transaction> getAllTransaction() {
        return transactionService.getAllTransaction();
    }

    @GetMapping("/{id}")
    public Optional<Transaction> getTransactionById(@RequestParam int id) {
        return transactionService.findTransactionbyId(id);
    }

    @PostMapping("/transaction")
    public Transaction createtreansaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    // @PutMapping("/transaction/{id}")
    // public ResponseEntity<Transaction> updateTransaction(@PathVariable int id, @RequestBody Transaction transaction) {
    //     try {
    //         transaction.setId(id);
    //         Transaction updatedTransaction = transactionService.updateTransaction(transaction);
    //         return ResponseEntity.ok(updatedTransaction);
    //     } catch (IllegalArgumentException e) {
    //         return ResponseEntity.notFound().build();
    //     }
    // }
    //Xóa 
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable int id) {
        transactionService.deleteTransaction(id);
        return;
    }
    /*------------------------------------Logic nâng cao -------------------------------*/
}
