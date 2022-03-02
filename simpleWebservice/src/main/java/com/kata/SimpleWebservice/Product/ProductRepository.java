package com.kata.SimpleWebservice.Product;

public interface ProductRepository {
    int save(Product product);
    Product getById(long id);
}
