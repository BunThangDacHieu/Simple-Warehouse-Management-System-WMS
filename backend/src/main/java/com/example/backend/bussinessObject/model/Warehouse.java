package com.example.backend.bussinessObject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "warehouse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // @NotNull(message = "Name is required")
    @Size(min = 5, message = "Name must at least 5 characters")
    private String name;
    // @NotNull(message = "Location is required, please enter location")
    @Size(min = 5, message = "Location must at least 5 characters")
    private String location;
    // @NotNull(message = "Capacity is required, please enter capacity")
    @Min(value = 1000, message = "Capacity must be greater than 0")
    private int capacity;
}
