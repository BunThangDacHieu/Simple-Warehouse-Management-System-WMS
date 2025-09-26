package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend.middleware.exception.BadRequestException;
import com.example.backend.middleware.exception.NotFoundException;
import com.example.backend.bussinessObject.model.Customer;
import com.example.backend.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /*----------------------------------CRUD cơ bản ----------------------------------- */
    public List<Customer> getAllCustomer() {
        List<Customer> customer = customerRepository.findAll();
        if (customer.isEmpty()) {
            throw new NotFoundException("Customer not found", 404);
        }
        return customer;
    }

    public Customer createCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().isEmpty() || customer.getName().isBlank()) {
            throw new BadRequestException("Customer name is required", 404);
        }
        return customerRepository.save(customer);
    }

    public Optional<Customer> findCustomerbyId(int id) {
        if (id <= 0) {
            throw new BadRequestException("Customer id must be greater than 0", 400);
        }
        return customerRepository.findById(id);
    }

    public void deleteCustomer(int id) {
        if (id <= 0) {
            throw new BadRequestException("Customer id must be greater than 0", 400);
        }
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Customer updateCustomer) {
        Customer customer = customerRepository.findById(updateCustomer.getId())
                .orElseThrow(() -> new NotFoundException("Customer not found", 404));
        customer.setAddress(updateCustomer.getAddress());
        customer.setEmail(updateCustomer.getEmail());
        customer.setName(updateCustomer.getName());
        customer.setPhone(updateCustomer.getPhone());
        customer.setDescription(updateCustomer.getDescription());
        return customerRepository.save(customer);
    }

}
