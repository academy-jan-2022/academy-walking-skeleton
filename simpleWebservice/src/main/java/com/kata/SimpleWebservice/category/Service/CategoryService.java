package com.kata.SimpleWebservice.category.Service;

import com.kata.SimpleWebservice.category.Model.Category;
import com.kata.SimpleWebservice.category.Model.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void create(Category category) {
        categoryRepository.save(category);

    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
