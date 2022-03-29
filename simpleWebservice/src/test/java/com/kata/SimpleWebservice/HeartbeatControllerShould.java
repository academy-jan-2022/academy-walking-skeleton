package com.kata.SimpleWebservice;

import com.kata.SimpleWebservice.Category.Model.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(HeartbeatController.class)
class HeartbeatControllerShould {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    HeartbeatController heartbeatController;

    @MockBean
    CategoryRepository categoryRepository;

    @MockBean
    CacheManager redisCacheManager;

    @Test
    void get_date_from_database() throws Exception {
        mockMvc.perform(get("/heartbeat"))
                .andExpect(status().isOk());

        verify(categoryRepository).findAll();
    }

    @Test
    void get_500_if_postgres_is_unavailable() throws Exception {
        when(categoryRepository.findAll()).thenThrow(new IllegalStateException("Failed to load ApplicationContext"));

        String response = mockMvc.perform(get("/heartbeat"))
                .andExpect(status().is5xxServerError())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(response).contains("database is not available");
    }

    @Test
    void get_500_if_redis_is_unavailable() throws Exception {
        when(redisCacheManager.getCacheNames()).thenThrow(new IllegalStateException("Failed to load ApplicationContext"));

        String response = mockMvc.perform(get("/heartbeat"))
                .andExpect(status().is5xxServerError())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(response).contains("cache is not available");
    }

    @Test
    void get_500_if_redis_and_database_is_unavailable() throws Exception {
        when(redisCacheManager.getCacheNames()).thenThrow(new IllegalStateException("Failed to load ApplicationContext"));
        when(categoryRepository.findAll()).thenThrow(new IllegalStateException("Failed to load ApplicationContext"));

        String response = mockMvc.perform(get("/heartbeat"))
                .andExpect(status().is5xxServerError())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(response).contains("database is not available");
        assertThat(response).contains("cache is not available");
    }
}