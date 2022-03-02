package com.kata.SimpleWebservice.Address;

import com.kata.SimpleWebservice.Address.Model.Address;
import com.kata.SimpleWebservice.Address.Model.Repository.AddressRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AddressRepositoryShould {

    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
        addressRepository.clear();
    }

    @Test
    void add_an_address() {
        var address = new Address(
                "line one test",
                "line two test",
                "city test",
                "TEST 1",
                "Test region",
                "test   country");

        var id = addressRepository.save(address);

        var result = addressRepository.getById(id);

        Assertions.assertEquals(1, result.getId());
    }


}
