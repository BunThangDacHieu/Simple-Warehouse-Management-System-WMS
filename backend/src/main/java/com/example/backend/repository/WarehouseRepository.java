package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.model.Warehouse;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    @Query("Select w.capacity, w.name from Warehouse w")
    List<Object[]> warehouseCapacity();
}
