package com.kata.SimpleWebservice.category.Controller;

import com.kata.SimpleWebservice.category.Model.Category;
import com.kata.SimpleWebservice.category.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Category> getAllCategoriesInDatabase() {
        return categoryService.getAllCategories();
    }

}
