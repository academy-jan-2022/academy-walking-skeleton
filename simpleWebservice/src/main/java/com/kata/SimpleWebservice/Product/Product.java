package com.kata.SimpleWebservice.Product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
public class Product {
    @JsonIgnore
    private long id;
    private String name;
    private int quantityPerUnit;
    private int unitPrice;
    private int unitsInStock;
    private int unitsInOrder;
    private int reorderLevel;
    private boolean discontinued;
    private int categoryId;

    public Product(String name, int quantityPerUnit, int unitPrice, int unitsInStock, int unitsInOrder, int reorderLevel, boolean discontinued, int categoryId) {
        this.name = name;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsInOrder = unitsInOrder;
        this.reorderLevel = reorderLevel;
        this.discontinued = discontinued;
        this.categoryId = categoryId;
    }
}
