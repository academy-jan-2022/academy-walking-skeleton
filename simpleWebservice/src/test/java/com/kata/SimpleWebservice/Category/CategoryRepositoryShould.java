package com.kata.SimpleWebservice.Category;

import com.kata.SimpleWebservice.Category.Model.Category;
import com.kata.SimpleWebservice.Category.Model.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class CategoryRepositoryShould {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    void increment_id() {
        Category entity1 = new Category("new name", "new description", "new picture");
        Category entity2 = new Category("new name2", "new description2", "new picture2");
        categoryRepository.save(entity1);
        categoryRepository.save(entity2);

        List<Category> categories = categoryRepository.findAll();

        Category foundCategory1 = categories.stream().filter(category -> category.getName().equals(entity1.getName())).findFirst().orElseThrow();
        Category foundCategory2 = categories.stream().filter(category -> category.getName().equals(entity2.getName())).findFirst().orElseThrow();

        Assertions.assertEquals(1, foundCategory1.getId());
        Assertions.assertEquals(2, foundCategory2.getId());
    }
}
