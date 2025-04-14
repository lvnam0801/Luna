package com.lvnam0801.Luna.Resource.Core.Manufacturer.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lvnam0801.Luna.Resource.Core.Manufacturer.Representation.Manufacturer;
import com.lvnam0801.Luna.Resource.Core.Manufacturer.Representation.ManufacturerRequest;

@RestController
@RequestMapping("/api/manufacturer")
public class ManufacturerController {

    private final JdbcTemplate jdbcTemplate;
    public ManufacturerController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/get-all")
    public Manufacturer[] getManufacturer()
    {
        String sql = "SELECT * FROM Manufacturer";
        Manufacturer[] manufacturers = jdbcTemplate.query(sql, (rs, rowNum) -> new Manufacturer(rs.getInt("ManufacturerID"), rs.getString("name"), rs.getString("LogoURL"), rs.getString("StreetAddress"), rs.getString("City"), rs.getString("StateProvince"), rs.getString("PostalCode"), rs.getString("Country"), rs.getString("Phone"), rs.getString("Email"), rs.getString("Website"), rs.getString("Status") )).toArray( Manufacturer[] :: new);
        return manufacturers;
    }

    @PostMapping("/create")
    public ResponseEntity<Manufacturer> createManufacturer(@RequestBody ManufacturerRequest request) {
        // SQL to insert a new Manufacturer
        String sql = """
            INSERT INTO Manufacturer 
                (Name, LogoURL, StreetAddress, City, StateProvince, PostalCode, Country, Phone, Email, Website, Status)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        // Execute the insert operation
        jdbcTemplate.update(sql, 
            request.name(), 
            request.logoURL(), 
            request.streetAddress(), 
            request.city(), 
            request.stateProvince(), 
            request.postalCode(), 
            request.country(), 
            request.phone(), 
            request.email(), 
            request.website(), 
            request.status()
        );

        // Retrieve the last inserted ManufacturerID
        Integer manufacturerId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        // Construct and return the created Manufacturer
        Manufacturer newManufacturer = new Manufacturer(
            manufacturerId,
            request.name(),
            request.logoURL(),
            request.streetAddress(),
            request.city(),
            request.stateProvince(),
            request.postalCode(),
            request.country(),
            request.phone(),
            request.email(),
            request.website(),
            request.status()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(newManufacturer);
    }

}
