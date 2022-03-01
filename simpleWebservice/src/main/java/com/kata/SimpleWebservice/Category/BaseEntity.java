package com.kata.SimpleWebservice.Category;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class BaseEntity {

    @JsonIgnore
    private @Id
    @GeneratedValue
    long id;
}
