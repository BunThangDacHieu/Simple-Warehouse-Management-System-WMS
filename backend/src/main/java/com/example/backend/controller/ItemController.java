package com.example.backend.controller;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.bussinessObject.model.Item;
import com.example.backend.service.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    /*----------------------------------CRUD cơ bản----------------------------*/
    @GetMapping
    public ResponseEntity<List<Item>> getAllItem() {
        try {
            List<Item> items = itemService.getAllItem();
            return ResponseEntity.ok(items);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Item>> findItembyId(@Valid @RequestParam int id) {
        try {
            Optional<Item> items = itemService.findItembyId(id);
            return ResponseEntity.ok(items);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        try {
            Item items = itemService.createItem(item);
            return ResponseEntity.ok(items);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable int id, @RequestBody Item item) {
        try {
            item.setId(id);
            Item updatedItem = itemService.updateItem(item);
            return ResponseEntity.ok(updatedItem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable int id) {
        try {
            itemService.deleteItem(id);
            return ResponseEntity.ok().body("Delete successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*-----------------------------------Nâng cao-------------------------*/
    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<Item>> findItemListbyWarehouseId(@PathVariable Long warehouseId){
        try {
            List<Item> items = itemService.findItemListbyWarehouseId(warehouseId);
            return ResponseEntity.ok(items);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
