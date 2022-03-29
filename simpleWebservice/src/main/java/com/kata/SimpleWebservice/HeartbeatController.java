package com.kata.SimpleWebservice;

import com.kata.SimpleWebservice.Category.Model.CategoryRepository;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

        HashMap<String, Runnable> dependencies = new HashMap<>();

        dependencies.put("database is not available", categoryRepository::findAll);
        dependencies.put("cache is not available", cacheManager::getCacheNames);

        for (Entry<String, Runnable> dependency : dependencies.entrySet()) {
            try {
                dependency.getValue().run();
            } catch (IllegalStateException e) {
                possibleErrors.add(dependency.getKey());
            }
        }

        if(!possibleErrors.isEmpty()){
            return new ResponseEntity<>(possibleErrors,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok().build();
    }


}
