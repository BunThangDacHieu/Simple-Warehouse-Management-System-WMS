package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import com.example.backend.model.Supplier;
import com.example.backend.repository.SupplierRepository;

public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    /*-------------------------------------CRUD cơ bản------------------------------------- */
    //Lấy danh sách của toàn bộ hàng hóa
    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAll();
    }

    //Tìm dựa trên id
    public Optional<Supplier> findSupplierbyId(int id) {
        return supplierRepository.findById(id);
    }

    //Xóa
    public void deleteSuppllier(int id) {
        supplierRepository.deleteById(id);
    }
    /*-------------------------------------Logic nghiệp vụ------------------------------------- */
}
