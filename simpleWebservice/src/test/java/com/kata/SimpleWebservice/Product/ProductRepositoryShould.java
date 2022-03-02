package com.kata.SimpleWebservice.Product;

import com.kata.SimpleWebservice.Category.Model.Category;
import com.kata.SimpleWebservice.Category.Model.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProductRepositoryShould {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test void
    should_create_and_retrieve_all_the_products_with_ok_status() throws Exception {

        Category category1 = new Category("new name", "new description", "new picture");
        Category category2 = new Category("new name2", "new description2", "new picture2");
        Category category3 = new Category("new name3", "new description2", "new picture2");
        int category1Id = categoryRepository.save(category1);
        int category2Id = categoryRepository.save(category2);
        int category3Id = categoryRepository.save(category3);

        Product product1 = new Product("Product One", 2,10, 20, 10, 10, false, category1Id);
        Product product2 = new Product("Product Two", 2, 10, 20, 10, 10, false, category2Id);

        int product1Id = productRepository.save(product1);
        int product2Id = productRepository.save(product2);

        Product foundProduct1 = productRepository.getById(product1Id);
        Product foundProduct2 = productRepository.getById(product2Id);

        Assertions.assertEquals(1, foundProduct1.getId());
        Assertions.assertEquals(2, foundProduct2.getId());


    }

}
