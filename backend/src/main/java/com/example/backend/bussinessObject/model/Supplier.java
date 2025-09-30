package com.example.backend.bussinessObject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "supplier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Supplier extends User {
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
}
