package com.example.backend.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.backend.bussinessObject.model.Warehouse;
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

    /*--------------------------------------Chart-------------------------------------------*/

    public List<Map<String, Object>> countCapacityofWarehoses() {
        try {
            List<Object[]> result = warehouseRepository.warehouseCapacity();
            List<Map<String, Object>> list = new ArrayList<>();
            for(Object[] obj : result){
                Map<String, Object> map = new HashMap<>();
                map.put("name", obj[0]);
                map.put("capacity", obj[1]);
                list.add(map);
            }
            return list;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /*-------------------------------------CRUD cơ bản------------------------------------- */
    //Lấy danh sách của toàn bộ hàng hóa
    public List<Warehouse> getAllWarehouse() {
        return warehouseRepository.findAll();
    }

    //Tìm dựa trên id
    @Cacheable(value = "findWarehousebyId", key = "#id")
    public Optional<Warehouse> findWarehousebyId(int id) {
        Map<String, String> errors = objectValidator.getRequestAndSummitErrors(id);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }
        return warehouseRepository.findById(id);
    }

    //Tạo Warehouse
    public Warehouse createWarehouse(Warehouse warehouse) {
        Map<String, String> errors = objectValidator.getRequestAndSummitErrors(warehouse);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }
        return warehouseRepository.save(warehouse);
    }

    //Cập nhật
    public Warehouse updateWarehouse(Warehouse updatedWarehouse) {
        Optional<Warehouse> existingWarehouse = warehouseRepository.findById(updatedWarehouse.getId());
        Map<String, String> errors = objectValidator.getRequestAndSummitErrors(updatedWarehouse);
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
        Map<String, String> errors = objectValidator.getRequestAndSummitErrors(id);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }
        warehouseRepository.deleteById(id);
    }
    /*-------------------------------------Logic nghiệp vụ------------------------------------- */

    //Số lượng mặt hàng trong một kho cụ thể, thông tin Inventory trong một Warehouse

}
