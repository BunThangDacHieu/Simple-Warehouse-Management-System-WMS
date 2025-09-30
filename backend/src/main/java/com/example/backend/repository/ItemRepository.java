package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.bussinessObject.model.Item;
import com.example.backend.bussinessObject.model.Warehouse;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i.item from Inventory i where i.warehouse.id =  :warehouseId")
    List<Item> findItemListbyWarehouseId(Long warehouseId);

}
