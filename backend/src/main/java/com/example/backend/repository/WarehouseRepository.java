package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

}
