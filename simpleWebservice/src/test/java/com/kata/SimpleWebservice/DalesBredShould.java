package com.kata.SimpleWebservice;

import com.kata.SimpleWebservice.Category.Model.Category;
import org.dalesbred.Database;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class DalesBredShould {

    @Autowired
    Database db;

    @Test
    void increment_id() {
        var result = db.findAll(Category.class, "select * from category");
        assertEquals(2, result.size());
    }
}
