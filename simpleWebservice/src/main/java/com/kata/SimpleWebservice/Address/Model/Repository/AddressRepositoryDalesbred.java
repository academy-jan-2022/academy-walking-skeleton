package com.kata.SimpleWebservice.Address.Model.Repository;

import com.kata.SimpleWebservice.Address.Model.Address;
import org.dalesbred.Database;
import org.springframework.stereotype.Repository;


@Repository
public class AddressRepositoryDalesbred implements AddressRepository {

    private final Database db;

    public AddressRepositoryDalesbred(Database db) {
        this.db = db;
    }

    @Override
    public int save(Address address) {

        return db.findUniqueInt(
                "insert into address (line_one,line_two,city,post_code,region,country) values (?,?,?,?,?,?) returning id",
                address.getLineOne(),
                address.getLineTwo(),
                address.getCity(),
                address.getPostCode(),
                address.getRegion(),
                address.getCountry());
    }

    @Override
    public Address getById(long id) {
        return db.findUnique(Address.class, "SELECT * FROM address WHERE id = ?", id);
    }

    @Override
    public void clear() {
        db.update("truncate address restart identity");
    }
}
