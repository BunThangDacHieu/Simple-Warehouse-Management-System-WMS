package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import com.example.backend.middleware.exception.BadRequestException;
import org.springframework.stereotype.Service;

import com.example.backend.bussinessObject.model.Supplier;
import com.example.backend.repository.SupplierRepository;

@Service
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

    //Tạo
    public Supplier createSupplier(Supplier supplier) {
        if (supplier.getName() == null || supplier.getName().isEmpty() || supplier.getName().isBlank()) {
            throw new BadRequestException("Customer name is required", 404);
        }
        return supplierRepository.save(supplier);
    }

    //Xóa
    public void deleteSuppllier(int id) {
        supplierRepository.deleteById(id);
    }
    /*-------------------------------------Logic nghiệp vụ------------------------------------- */
}
