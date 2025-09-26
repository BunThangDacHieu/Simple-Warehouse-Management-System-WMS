package com.example.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.bussinessObject.model.Supplier;
import com.example.backend.service.SupplierService;
import com.example.backend.util.ObjectValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private ObjectValidator objectValidator;
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    /*-----------------------------------CRUD cơ bản ------------------------------------- */
    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSupplier() {
        try {
            List<Supplier> suppliers = supplierService.getAllSupplier();
            return ResponseEntity.ok(suppliers);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

//Tạo một Suppplier riêng
    @PostMapping()
    public ResponseEntity<Supplier> createSupplier(@Valid @RequestBody Supplier supplier) {
        try {
            return ResponseEntity.ok(supplierService.createSupplier(supplier));
        }catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Supplier>> findSupplierbyId(@Valid @RequestParam int id) {
        try {
            Optional<Supplier> suppliers = supplierService.findSupplierbyId(id);
            return ResponseEntity.ok(suppliers);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSuppllier(@Valid @PathVariable int id) {
        try {
            supplierService.deleteSuppllier(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        /*-----------------------------------Logic nâng cao ------------------------------------- */
    }
}
