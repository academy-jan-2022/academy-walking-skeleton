package com.kata.SimpleWebservice.Address.Model.Repository;


import com.kata.SimpleWebservice.Address.Model.Address;

public interface AddressRepository {
    void save(Address address);
    int getById(long id);
}
