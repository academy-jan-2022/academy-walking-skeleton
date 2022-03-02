package com.kata.SimpleWebservice.Category.Model;

import java.util.List;

public interface CategoryRepository {
    int save(Category category);
    List<Category> findAll();
}
