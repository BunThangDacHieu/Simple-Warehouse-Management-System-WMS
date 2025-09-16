package com.example.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Supplier;
import com.example.backend.service.SupplierService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    /*-----------------------------------CRUD cơ bản ------------------------------------- */
    @GetMapping
    public List<Supplier> getAllSupplier() {
        return supplierService.getAllSupplier();
    }

    @GetMapping("/{id}")
    public Optional<Supplier> findSupplierbyId(@RequestParam int id) {
        return supplierService.findSupplierbyId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSuppllier(@PathVariable int id) {
        supplierService.deleteSuppllier(id);

        /*-----------------------------------Logic nâng cao ------------------------------------- */
    }
}
