package com.example.backend.bussinessObject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "User")
// @Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Min(value = 1, message = "ID must be greater than 0")
    private int id;
    @NotNull
    @Size(min = 10, max = 20, message = "Tên phải có ít nhất 10 chữ cái, đầy đủ tên")
    private String name;
    @NotNull
    @Email(message = "Cần đúng định dạng Email")
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
            message = "Mật khẩu cần bao gồm chữ cái viết Hoa, chữ thường, số, và kí tự đặc biệt và có độ dài ít nhất là 8"
    )
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        SUPPLIER,
        MANAGER,
        CUSTOMER
    }
    @Size(min = 10, max = 20, message = "Tên của người liên hệ cần ít nhất 10 chữ cái, và đầy đủ họ và tên")
    private String contract_person;
    @NotNull
    @Pattern(regexp = "^0[0-9]{9}$", message="Cần bắt đầu bằng số 0")
    private String phone;
    @NotBlank(message = "Làm ơn, không được làm trống địa chỉ")
    @Pattern(
            regexp = "^(?=.*\\p{L})[\\p{L}0-9 ,.-]+$",
            message = "Địa chỉ phải chính xác"
    )
    private String address;
    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Customer customer;

    public User() {
    }

    public User(int id, String name, String email, String password, Role role,
            String contract_person, String phone, String address, Customer customer) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getContract_person() {
        return contract_person;
    }

    public void setContract_person(String contract_person) {
        this.contract_person = contract_person;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
