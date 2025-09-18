package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend.exception.BadRequestException;
import com.example.backend.exception.NotFoundException;
import com.example.backend.model.Inventory;
import com.example.backend.repository.InventoryRepository;
import com.example.backend.repository.WarehouseRepository;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final WarehouseRepository warehouseRepository;

    public InventoryService(InventoryRepository inventoryRepository, WarehouseRepository warehouseRepository) {
        this.inventoryRepository = inventoryRepository;
        this.warehouseRepository = warehouseRepository;
    }

    /*------------------------------------------CRUD cơ bản--------------------------------------- */
    //Hiện danh sách hàng tồn kho
    public List<Inventory> getAllInventories() {
        List<Inventory> inventory = inventoryRepository.findAll();
        if (inventory.isEmpty() || inventory == null) {
            throw new NotFoundException("Inventory not found", 404);
        }
        return inventoryRepository.findAll();

    }

    //Tạo
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);

    }

    //Tìm kiếm dựa trên ID
    public Optional<Inventory> getInventoryById(int id) {
        if (id <= 0) {
            throw new BadRequestException("Inventory id must be greater than 0", 400);
        }
        return inventoryRepository.findById(id);
    }

    //Cập nhật    
    public Inventory updateInventory(Inventory updateInventory) throws Exception {
        Inventory inventory = inventoryRepository.findById(updateInventory.getId())
                .orElseThrow(() -> new NotFoundException("Inventory not found", 404));
        inventory.setQuantity(updateInventory.getQuantity());
        return inventoryRepository.save(inventory);

    }

    //Xóa
    public void deleteInventory(int id) {
        inventoryRepository.deleteById(id);

    }

    /*------------------------------------------Logic nghiệp vụ---------------------------------------*/
    //Inventory dựa trên warehouse_id
    public List<Inventory> getInventoryByWarehouseId(int warehouseId) {
        return inventoryRepository.findByWarehouseId(warehouseId);
    }

}
