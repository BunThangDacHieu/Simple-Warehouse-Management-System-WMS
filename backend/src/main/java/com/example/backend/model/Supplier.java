package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "ID must be greater than 0")
    private int id;
    @NotEmpty
    @Size(min = 5, max = 20, message = "Name must at least 5 characters")
    private String name;
    private String address;
    @Pattern(regexp = "^0[0-9]{9}$", message = "Phone must be 10 digits, and start with number 0")
    private String phone;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String contract_person;
    @Size(min = 5, max = 500, message = "Description must at least 5 characters, description must less than 500 characters")
    private String description;
    @Email
    private String email;

    public Supplier() {
    }

    public Supplier(int id, String name, String contract_person, String address, String phone, String description, String email) {
        this.id = id;
        this.name = name;
        this.contract_person = contract_person;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContract_person() {
        return contract_person;
    }

    public void setContract_person(String contract_person) {
        this.contract_person = contract_person;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
