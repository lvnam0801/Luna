package com.lvnam0801.Luna.Resource.Core.Address.Controller;

import com.lvnam0801.Luna.Resource.Core.Address.Representation.Address;
import com.lvnam0801.Luna.Resource.Core.Address.Representation.AddressCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-all")
    public Address[] getAllAddresses() {
        String sql = "SELECT * FROM Address";

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new Address(
                rs.getInt("AddressID"),
                rs.getString("StreetAddress"),
                rs.getString("City"),
                rs.getString("StateProvince"),
                rs.getString("PostalCode"),
                rs.getString("Country")
            )
        ).toArray(Address[]::new);
    }

    @PostMapping("/create")
    public ResponseEntity<Address> createAddress(@RequestBody AddressCreateRequest request) {
        String sql = """
            INSERT INTO Address (StreetAddress, City, StateProvince, PostalCode, Country)
            VALUES (?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            request.streetAddress(),
            request.city(),
            request.stateProvince(),
            request.postalCode(),
            request.country()
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        Address newAddress = new Address(
            id,
            request.streetAddress(),
            request.city(),
            request.stateProvince(),
            request.postalCode(),
            request.country()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(newAddress);
    }
}