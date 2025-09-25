package com.example.backend.controller;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /*----------------------------------Ngoài lề----------------------------------------*/
    @GetMapping("/warehouse-capacity")
    public ResponseEntity<List<Map<String, Object>>> warehouseCapacity() {
        return ResponseEntity.ok(warehouseService.countCapacityofWarehoses());
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
