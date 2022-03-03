package com.kata.SimpleWebservice.category.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class Category {

    @JsonIgnore
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String name;
    private String description;
    private String picture;

    public Category(String name, String description, String picture) {
        this.name = name;
        this.description = description;
        this.picture = picture;
    }


}
