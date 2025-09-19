package com.example.backend.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.model.Warehouse;
import com.example.backend.repository.WarehouseRepository;
import com.example.backend.util.ObjectValidator;

@Service
public class WarehouseService {

    @Autowired
    private ObjectValidator objectValidator;
    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    /*-------------------------------------CRUD cơ bản------------------------------------- */
    //Lấy danh sách của toàn bộ hàng hóa
    public List<Warehouse> getAllWarehouse() {
        // Map<String, String> errors = objectValidator.getRequestAndSubmitErrors();
        // // if (!errors.isEmpty()) {
        // //     throw new IllegalArgumentException(errors.toString());
        // // }
        return warehouseRepository.findAll();
    }

    //Tìm dựa trên id
    public Optional<Warehouse> findWarehousebyId(int id) {
        Map<String, String> errors = objectValidator.getRequestAndSubmitErrors(id);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }
        return warehouseRepository.findById(id);
    }

    //Tạo Warehouse
    public Warehouse createWarehouse(Warehouse warehouse) {
        Map<String, String> errors = objectValidator.getRequestAndSubmitErrors(warehouse);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }
        return warehouseRepository.save(warehouse);
    }

    //Cập nhật
    public Warehouse updateWarehouse(Warehouse updatedWarehouse) {
        Optional<Warehouse> existingWarehouse = warehouseRepository.findById(updatedWarehouse.getId());
        Map<String, String> errors = objectValidator.getRequestAndSubmitErrors(updatedWarehouse);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }
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
        Map<String, String> errors = objectValidator.getRequestAndSubmitErrors(id);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }
        warehouseRepository.deleteById(id);
    }
    /*-------------------------------------Logic nghiệp vụ------------------------------------- */

    //Số lượng mặt hàng trong một kho cụ thể, thông tin Inventory trong một Warehouse
}
