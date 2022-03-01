package com.kata.SimpleWebservice.Category.Model;


import com.kata.SimpleWebservice.Category.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
