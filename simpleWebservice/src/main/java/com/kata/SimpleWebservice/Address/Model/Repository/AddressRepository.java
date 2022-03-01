package com.kata.SimpleWebservice.Address.Model.Repository;

import com.kata.SimpleWebservice.Address.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
