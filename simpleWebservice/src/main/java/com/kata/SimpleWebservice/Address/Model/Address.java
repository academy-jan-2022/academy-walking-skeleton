package com.kata.SimpleWebservice.Address.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;

@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
    @JsonIgnore
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(nullable = false)
    private String lineOne;
    @Column(nullable = false)
    private String lineTwo;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String postCode;
    @Column(nullable = false)
    private String region;
    @Column(nullable = false)
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
