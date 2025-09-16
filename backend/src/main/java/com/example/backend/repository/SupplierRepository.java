package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
