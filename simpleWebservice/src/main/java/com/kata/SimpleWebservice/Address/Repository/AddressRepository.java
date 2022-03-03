package com.kata.SimpleWebservice.Address.Repository;


import com.kata.SimpleWebservice.Address.Model.Address;

public interface AddressRepository {
    int save(Address address);
    Address getById(long id);

    void clear();
}
