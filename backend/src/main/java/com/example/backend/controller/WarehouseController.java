package com.example.backend.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Warehouse;
import com.example.backend.service.WarehouseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    /*-----------------------------------CRUD cơ bản----------------------------------- */
    @GetMapping
    public List<Warehouse> getAllWarehouse() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouse();
        return warehouses.isEmpty() ? Collections.emptyList() : warehouses;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Warehouse>> getWarehouseById(@Valid @PathVariable int id) {
        try {
            Optional<Warehouse> warehouse = warehouseService.findWarehousebyId(id);
            return ResponseEntity.ok(warehouse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@Valid @RequestBody Warehouse warehouse) {
        try {
            warehouseService.createWarehouse(warehouse);
            return ResponseEntity.ok(warehouse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWarehouse(@PathVariable int id, @Valid @RequestBody Warehouse warehouse) {
        try {
            warehouse.setId(id);
            Warehouse updatedWarehouse = warehouseService.updateWarehouse(warehouse);
            return ResponseEntity.ok(updatedWarehouse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWarehouse(@Valid @PathVariable int id) {
        try {
            warehouseService.deleteWarehouse(id);
            return ResponseEntity.ok("Delete successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*----------------------------------- Logic nghiệp vụ ------------------------------*/
}
