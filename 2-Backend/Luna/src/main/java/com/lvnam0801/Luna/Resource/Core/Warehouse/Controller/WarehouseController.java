package com.lvnam0801.Luna.Resource.Core.Warehouse.Controller;

import com.lvnam0801.Luna.Resource.Core.Warehouse.Representation.Warehouse;
import com.lvnam0801.Luna.Resource.Core.Warehouse.Representation.WarehouseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-all")
    public Warehouse[] getWarehouses() {
        String sql = "SELECT * FROM Warehouse";

        Warehouse[] warehouses = jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new Warehouse(
                rs.getInt("WarehouseID"),
                rs.getString("Name"),
                rs.getString("Phone"),
                rs.getString("Email"),
                rs.getString("StreetAddress"),
                rs.getString("City"),
                rs.getString("StateProvince"),
                rs.getString("PostalCode"),
                rs.getString("Country"),
                rs.getString("Status")
            )
        ).toArray(Warehouse[]::new);

        return warehouses;
    }

    @PostMapping("/create")
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody WarehouseRequest request) {
        String sql = """
            INSERT INTO Warehouse (Name, Phone, Email, StreetAddress, City, StateProvince, PostalCode, Country, Status)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            request.name(),
            request.phone(),
            request.email(),
            request.streetAddress(),
            request.city(),
            request.stateProvince(),
            request.postalCode(),
            request.country(),
            request.status()
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        Warehouse warehouse = new Warehouse(
            id,
            request.name(),
            request.phone(),
            request.email(),
            request.streetAddress(),
            request.city(),
            request.stateProvince(),
            request.postalCode(),
            request.country(),
            request.status()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(warehouse);
    }
}