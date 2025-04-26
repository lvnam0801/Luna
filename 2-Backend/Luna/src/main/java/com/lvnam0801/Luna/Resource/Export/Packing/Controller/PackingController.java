package com.lvnam0801.Luna.Resource.Export.Packing.Controller;

import com.lvnam0801.Luna.Resource.Export.Packing.Representation.Packing;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/packing")
public class PackingController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-by-order/{orderID}")
    public Packing[] getByOrder(@PathVariable Integer orderID) {
        String sql = "SELECT * FROM Packing WHERE OrderID = ?";

        return jdbcTemplate.query(
            sql,
            new Object[]{orderID},
            (rs, rowNum) -> new Packing(
                rs.getInt("PackingID"),
                rs.getInt("OrderID"),
                rs.getInt("PackedBy"),
                rs.getDate("PackingDate"),
                rs.getString("PackageNumber"),
                rs.getDouble("PackageWeight"),
                rs.getString("PackageDimension"),
                rs.getString("Notes"),
                rs.getString("Status")
            )
        ).toArray(Packing[]::new);
    }

    @PostMapping("/create")
    public ResponseEntity<Packing> createPacking(@RequestBody PackingRequest request) {
        String sql = """
            INSERT INTO Packing (
                OrderID, PackedBy, PackingDate, PackageNumber,
                PackageWeight, PackageDimension, Notes, Status
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            request.orderID(),
            request.packedBy(),
            request.packingDate(),
            request.packageNumber(),
            request.packageWeight(),
            request.packageDimension(),
            request.notes(),
            request.status()
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        Packing packing = new Packing(
            id,
            request.orderID(),
            request.packedBy(),
            request.packingDate(),
            request.packageNumber(),
            request.packageWeight(),
            request.packageDimension(),
            request.notes(),
            request.status()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(packing);
    }
}