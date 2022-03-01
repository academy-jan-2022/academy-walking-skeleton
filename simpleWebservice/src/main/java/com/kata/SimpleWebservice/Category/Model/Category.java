package com.kata.SimpleWebservice.Category.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Category {

    @JsonIgnore
    private @Id @GeneratedValue
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
