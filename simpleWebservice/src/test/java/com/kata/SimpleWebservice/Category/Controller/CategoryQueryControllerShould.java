package com.kata.SimpleWebservice.Category.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.SimpleWebservice.Category.Model.Category;
import com.kata.SimpleWebservice.Category.Service.CategoryService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryQueryController.class)
public class CategoryQueryControllerShould {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void
    call_category_service_to_return_all_categories() throws Exception {
        // Given
        List<Category> expectedReturn = new ArrayList<Category>();
        Category category = new Category("aName", "aDescription", "aPicture");
        expectedReturn.add(category);

        when(categoryService.getAllCategories()).thenReturn(expectedReturn);

        var content = mockMvc.perform(get("/allCategories"))
                        .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(objectMapper.writeValueAsString(expectedReturn), content);
    }

}
