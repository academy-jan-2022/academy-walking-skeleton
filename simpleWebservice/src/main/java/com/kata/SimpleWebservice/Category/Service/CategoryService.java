package com.kata.SimpleWebservice.Category.Service;

import com.kata.SimpleWebservice.Category.Model.Category;
import com.kata.SimpleWebservice.Category.Model.CategoryRepository;
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
        throw new UnsupportedOperationException();
    }
}
