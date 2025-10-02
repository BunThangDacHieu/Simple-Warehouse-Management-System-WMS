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
@PrimaryKeyJoinColumn(name = "user_id")
public class Supplier extends User {
    private String contractPerson;
    @Size(min = 5, max = 500, message = "Description must at least 5 characters, description must less than 500 characters")
    private String description;

}
