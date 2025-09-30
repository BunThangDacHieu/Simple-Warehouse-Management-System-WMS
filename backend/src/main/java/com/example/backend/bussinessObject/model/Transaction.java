package com.example.backend.bussinessObject.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "ID must be greater than 0")
    private int id;
    @Size(min = 1, message = "Quantity must be greater than 0")
    private int quantity;
    @NotNull
    @Size(min = 4, message = "Type is required")
    private String type;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String date;
    @Size(min = 5, max = 200, message = "Note must at least 5 characters")
    private String note;
}
