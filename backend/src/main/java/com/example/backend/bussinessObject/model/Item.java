package com.example.backend.bussinessObject.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "supplier")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private int quantity;
    private String itemId;

    private enum Unit {
        KILOGRAM,
        PIECE,
        BOX
    }

    @Enumerated(EnumType.STRING)
    public Unit unit;
    @ManyToMany
    @JoinTable(
            name = "supplier_item",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id")
    )
    @JsonIgnore
    private List<Supplier> supplier;
}
