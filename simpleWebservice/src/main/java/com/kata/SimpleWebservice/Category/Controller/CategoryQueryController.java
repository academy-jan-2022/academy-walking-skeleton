package com.kata.SimpleWebservice.Category.Controller;

import com.kata.SimpleWebservice.Category.Model.Category;
import com.kata.SimpleWebservice.Category.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CategoryQueryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/allCategories")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(cacheNames = "categories")
    public List<Category> getAllCategoriesInDatabase() {
        return categoryService.getAllCategories();
    }

}
