package com.kata.SimpleWebservice.category.Model;

import java.util.List;

public interface CategoryRepository {
    int save(Category category);
    List<Category> findAll();

    void clear();
}
