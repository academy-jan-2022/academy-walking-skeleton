package com.kata.SimpleWebservice.Category.Service;

import com.kata.SimpleWebservice.Category.Model.Category;
import com.kata.SimpleWebservice.Category.Model.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void create(Category category) {
        categoryRepository.save(category);

    }
}
