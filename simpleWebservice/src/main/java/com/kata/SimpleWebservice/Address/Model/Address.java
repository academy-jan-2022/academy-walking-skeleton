package com.kata.SimpleWebservice.Address.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;

@NoArgsConstructor
@Data
public class Address {
    @JsonIgnore
    private long id;
    private String lineOne;
    private String lineTwo;
    private String city;
    private String postCode;
    private String region;
    private String country;

    public Address(String lineOne, String lineTwo, String city, String postCode, String region, String country) {
        this.lineOne = lineOne;
        this.lineTwo = lineTwo;
        this.city = city;
        this.postCode = postCode;
        this.region = region;
        this.country = country;
    }
}
