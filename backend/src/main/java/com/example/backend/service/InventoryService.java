package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import org.joda.time.IllegalInstantException;
import org.springframework.stereotype.Service;

import com.example.backend.model.Inventory;
import com.example.backend.repository.InventoryRepository;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /*------------------------------------------CRUD cơ bản--------------------------------------- */
    //Hiện danh sách hàng tồn kho
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();

    }

    //Tạo
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    //Tìm kiếm dựa trên ID
    public Optional<Inventory> getInventoryById(int id) {
        return inventoryRepository.findById(id);
    }

    //Cập nhật    
    public Inventory updateInventory(Inventory updateInventory) throws Exception {
        Inventory inventory = inventoryRepository.findById(updateInventory.getId())
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found"));
        inventory.setQuantity(updateInventory.getQuantity());
        return inventoryRepository.save(inventory);

    }

    //Xóa
    public void deleteInventory(int id) {
        inventoryRepository.deleteById(id);
    }
    /*------------------------------------------Logic nghiệp vụ---------------------------------------*/

}
