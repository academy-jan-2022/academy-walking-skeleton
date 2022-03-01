package com.kata.SimpleWebservice.Category.Model;


import com.kata.SimpleWebservice.Category.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository {
    void save(Category category);
    List<Category> findAll();
}
