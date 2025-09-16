package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend.model.Warehouse;
import com.example.backend.repository.WarehouseRepository;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    /*-------------------------------------CRUD cơ bản------------------------------------- */
    //Lấy danh sách của toàn bộ hàng hóa
    public List<Warehouse> getAllWarehouse() {
        return warehouseRepository.findAll();
    }

    //Tìm dựa trên id
    public Optional<Warehouse> findWarehousebyId(int id) {
        return warehouseRepository.findById(id);
    }

    //Tạo Warehouse
    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    //Cập nhật
    public Warehouse updateWarehouse(Warehouse updatedWarehouse) {
        Optional<Warehouse> existingWarehouse = warehouseRepository.findById(updatedWarehouse.getId());
        if (existingWarehouse.isPresent()) {
            Warehouse warehouse = existingWarehouse.get();
            warehouse.setName(updatedWarehouse.getName());
            warehouse.setLocation(updatedWarehouse.getLocation());
            warehouse.setCapacity(updatedWarehouse.getCapacity());
            return warehouseRepository.save(warehouse);
        } else {
            throw new IllegalArgumentException("Warehouse not found");
        }
    }

    //Xóa
    public void deleteWarehouse(int id) {
        warehouseRepository.deleteById(id);
    }
    /*-------------------------------------Logic nghiệp vụ------------------------------------- */
}
