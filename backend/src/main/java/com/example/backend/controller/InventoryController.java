package com.example.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.bussinessObject.model.Inventory;
import com.example.backend.service.InventoryService;

import jakarta.validation.Valid;

@SuppressWarnings("JvmTaintAnalysis")
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    //----------------------------------------CRUD cơ bản---------------------------------------//
    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventories() {
        List<Inventory> inventories = inventoryService.getAllInventories();
        return ResponseEntity.ok(inventories);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventorybyId(@Valid @PathVariable int id) {
        Optional<Inventory> inventory = inventoryService.getInventoryById(id);
        return inventory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("JvmTaintAnalysis")
    @PostMapping
    public ResponseEntity<Inventory> createInventory(@Valid @RequestBody Inventory inventory) {
        Inventory createInventory = inventoryService.createInventory(inventory);
        return new ResponseEntity<>(createInventory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Inventory> deleteInventory(@Valid @PathVariable int id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@Valid @PathVariable int id, @RequestBody Inventory updateInventory) {
        try {
            updateInventory.setId(id);
            Inventory updated = inventoryService.updateInventory(updateInventory);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //----------------------------------------Logic nâng cao---------------------------------------//

    @GetMapping("/warehouse/{id}")
    public ResponseEntity<List<Inventory>> getInventoryByWarehouseId(@Valid @PathVariable int id) {
        List<Inventory> inventories = inventoryService.getInventoryByWarehouseId(id);
        return ResponseEntity.ok(inventories);
    }

}
