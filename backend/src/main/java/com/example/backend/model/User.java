package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "User")
// @Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Min(value = 1, message = "ID must be greater than 0")
    private int id;
    @NotNull
    @Size(min = 10, max = 20, message = "Name must greater than 10 characters and less than 20 characters abc")
    private String name;
    @Email
    @NotNull
    private String email;
    @NotNull
    @Pattern(regexp = ".*\\d.*", message = "must contain at least one numeric character")
    private String password;
    @Enumerated(EnumType.STRING)
    private role role;

    public enum role {
        SUPPLIER,
        MANAGER,
        CUSTOMER
    }
    @Size(min = 5, max = 20, message = "Name of contract person must greater than 5 characters and less than 20 characters")
    private String contract_person;
    @Digits(integer = 10, fraction = 0, message = "Phone must be up to 10 digits")
    private int phone;

    private String address;
    @OneToOne(mappedBy = "user")
    private Customer customer;

    public User() {
    }

    public User(int id, String name, String email, String password, com.example.backend.model.User.role role,
            String contract_person, int phone, String address, Customer customer) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.contract_person = contract_person;
        this.phone = phone;
        this.address = address;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public role getRole() {
        return role;
    }

    public void setRole(role role) {
        this.role = role;
    }

    public String getContract_person() {
        return contract_person;
    }

    public void setContract_person(String contract_person) {
        this.contract_person = contract_person;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
