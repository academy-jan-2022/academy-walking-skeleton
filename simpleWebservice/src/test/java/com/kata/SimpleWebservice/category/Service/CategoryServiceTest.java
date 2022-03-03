package com.kata.SimpleWebservice.category.Service;

import com.kata.SimpleWebservice.category.Model.Category;
import com.kata.SimpleWebservice.category.Model.CategoryRepository;
import org.junit.jupiter.api.Test;

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

    @Test void
    get_all_the_categories_from_the_database() {

        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        CategoryService categoryService = new CategoryService(categoryRepository);

        categoryService.getAllCategories();

        verify(categoryRepository).findAll();
    }
}