package com.kata.SimpleWebservice.Product;

import org.dalesbred.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryDalesbread implements ProductRepository{

    @Autowired
    Database db;

    @Override
    public int save(Product product) {
        return db.findUniqueInt(
                String.format("insert into product (name, quantity_per_unit, unit_price, units_in_stock, units_in_order, reorder_level, discontinued, category_id) values ('%s', %s, %s, %s, %s, %s, %s, %s) returning id",
                        product.getName(), product.getQuantityPerUnit(), product.getUnitPrice(), product.getUnitsInStock(), product.getUnitsInOrder(), product.getReorderLevel(), product.isDiscontinued(), product.getCategoryId())
        );
    }

    @Override
    public Product getById(long id) {
        return db.findUnique(Product.class, "select id, name, quantity_per_unit, unit_price, units_in_stock, units_in_order, reorder_level, discontinued, category_id from product where id = ?", id);
    }
}
