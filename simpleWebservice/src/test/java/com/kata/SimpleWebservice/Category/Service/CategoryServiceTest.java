package com.kata.SimpleWebservice.Category.Service;

import com.kata.SimpleWebservice.Category.Model.Category;
import com.kata.SimpleWebservice.Category.Model.CategoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CategoryServiceTest {
    @Test void
    create_a_new_category_in_the_database() {
        // Given
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        CategoryService categoryService = new CategoryService(categoryRepository);
        Category category = new Category("aName", "aDescription", "aPicture");
        // When
        categoryService.create(category);

        // Then
        verify(categoryRepository).save(category);
    }
}