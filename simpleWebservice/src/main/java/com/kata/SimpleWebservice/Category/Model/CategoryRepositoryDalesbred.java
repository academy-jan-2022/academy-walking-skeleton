package com.kata.SimpleWebservice.Category.Model;

import org.dalesbred.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryDalesbred implements CategoryRepository {
    @Autowired
    Database db;

    @Override
    public int save(Category category) {
       return db.findUniqueInt(
               String.format("insert into category (name, description, picture) values ('%s', '%s', '%s') returning id",
                       category.getName(), category.getDescription(), category.getPicture())
       );
    }

    @Override
    public List<Category> findAll() {
        return db.findAll(Category.class, "select * from category");
    }
}
