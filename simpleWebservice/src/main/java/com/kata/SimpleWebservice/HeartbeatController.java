package com.kata.SimpleWebservice;

import com.kata.SimpleWebservice.Category.Model.CategoryRepository;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class HeartbeatController {

    private final CategoryRepository categoryRepository;
    private final CacheManager cacheManager;

    public HeartbeatController(CategoryRepository categoryRepository, CacheManager cacheManager) {
        this.categoryRepository = categoryRepository;
        this.cacheManager = cacheManager;
    }


    @GetMapping("/heartbeat")
    public ResponseEntity<?> checkHeartbeat() {
        List<String> possibleErrors = new ArrayList<>();
        try {
            categoryRepository.findAll();
        } catch (IllegalStateException e) {
            possibleErrors.add("database is not available");
        }

        try {
            cacheManager.getCacheNames();
        } catch (IllegalStateException e) {
            possibleErrors.add("cache is not available");
        }

        if(!possibleErrors.isEmpty()){
            return new ResponseEntity<>(possibleErrors,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok().build();


    }


}
