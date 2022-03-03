package com.kata.SimpleWebservice.Category.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.SimpleWebservice.Category.Model.Category;
import com.kata.SimpleWebservice.Category.Service.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryCacheShould {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CategoryService categoryService;

    @Autowired
    RedisCacheManager redisCacheManager;

    @BeforeEach
    void wipe_redis() {
        for (String name : redisCacheManager.getCacheNames()) {
            redisCacheManager.getCache(name).clear();
        }
    }

    @Test void
    create_a_category_and_when_retrieving_it_cache_it() throws Exception {
        Category cachedCategory = new Category("cachedNames", "cachedDescription", "cachedPicture");
        Category uncachedCategory = new Category("uncachedNames", "uncachedDescription", "uncachedPicture");

        when(categoryService.getAllCategories()).thenReturn(List.of(cachedCategory)).thenReturn(List.of(uncachedCategory));

        var requestOneContent = mockMvc.perform(get("/allCategories"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var requestTwoContent = mockMvc.perform(get("/allCategories"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertEquals(objectMapper.writeValueAsString(List.of(cachedCategory)), requestTwoContent);
        verify(categoryService, times(1)).getAllCategories();
    }

}
