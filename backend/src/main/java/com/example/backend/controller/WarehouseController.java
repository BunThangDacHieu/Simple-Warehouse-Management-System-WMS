package com.example.backend.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Warehouse;
import com.example.backend.service.WarehouseService;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    /*-----------------------------------CRUD cơ bản----------------------------------- */
    @GetMapping("/")
    public List<Warehouse> getAllWarehouse() {
        return warehouseService.getAllWarehouse();
    }

    @GetMapping("/{id}")
    public Optional<Warehouse> getWarehouseById(@RequestParam int id) {
        return warehouseService.findWarehousebyId(id);
    }

    @PostMapping
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.createWarehouse(warehouse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWarehouse(@PathVariable int id, @RequestBody Warehouse warehouse) {
        try {
            warehouse.setId(id);
            Warehouse updatedWarehouse = warehouseService.updateWarehouse(warehouse);
            return ResponseEntity.ok(updatedWarehouse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteWarehouse(@PathVariable int id) {
        warehouseService.deleteWarehouse(id);
    }

    /*----------------------------------- Logic nghiệp vụ ------------------------------*/
}
