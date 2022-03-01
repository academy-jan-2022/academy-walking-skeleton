package com.kata.SimpleWebservice.Category;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kata.SimpleWebservice.Category.Model.Category;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @JsonIgnore
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String name;
    private int quantityPerUnit;
    private int unitPrice;
    private int unitsInStock;
    private int unitsInOrder;
    private int reorderLevel;
    private boolean discontinued;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String name, int quantityPerUnit, int unitPrice, int unitsInStock, int unitsInOrder, int reorderLevel, boolean discontinued, Category category) {
        this.name = name;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsInOrder = unitsInOrder;
        this.reorderLevel = reorderLevel;
        this.discontinued = discontinued;
        this.category = category;
    }
}
