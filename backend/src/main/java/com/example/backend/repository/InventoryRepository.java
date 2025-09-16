package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

}
