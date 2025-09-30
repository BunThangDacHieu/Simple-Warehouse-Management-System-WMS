package com.example.backend.bussinessObject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "ID must be greater than 0")
    private int id;
    @Size(min = 5, max = 20, message = "Name must at least 5 characters")
    private String name;
    private String address;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Pattern(regexp = "^0[0-9]{9}$")
    private String phone;
    @Email
    private String email;
    @Size(min = 5, max = 500, message = "Description must at least 5 characters, description must less than 500 characters")
    private String description;
}
