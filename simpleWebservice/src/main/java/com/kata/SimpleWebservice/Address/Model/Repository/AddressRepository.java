package com.kata.SimpleWebservice.Address.Model.Repository;


import com.kata.SimpleWebservice.Address.Model.Address;

public interface AddressRepository {
    int save(Address address);
    Address getById(long id);

    void clear();
}
