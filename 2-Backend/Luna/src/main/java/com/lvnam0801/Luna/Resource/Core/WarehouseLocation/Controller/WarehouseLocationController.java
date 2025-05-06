package com.lvnam0801.Luna.Resource.Core.WarehouseLocation.Controller;

import com.lvnam0801.Luna.Resource.Core.WarehouseLocation.Representation.WarehouseLocation;
import com.lvnam0801.Luna.Resource.Core.WarehouseLocation.Representation.WarehouseLocationCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/warehouse-location")
public class WarehouseLocationController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-all")
    public WarehouseLocation[] getLocations() {
        String sql = "SELECT * FROM Location";

        WarehouseLocation[] locations = jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new WarehouseLocation(
                rs.getInt("LocationID"),
                rs.getString("LocationName"),
                rs.getString("LocationType"),
                rs.getString("StorageType"),
                rs.getString("Unit"),
                rs.getInt("Capacity"),
                rs.getString("Status"),
                rs.getInt("WarehouseID")
            )
        ).toArray(WarehouseLocation[]::new);

        return locations;
    }

    @GetMapping("/get-by-id/{locationID}")
    public WarehouseLocation getLocationByID(@PathVariable String locationID) {
        String sql = "SELECT * FROM Location WHERE LocationID=?";

        WarehouseLocation location = jdbcTemplate.queryForObject(
            sql,
            (rs, rowNum) -> new WarehouseLocation(
                rs.getInt("LocationID"),
                rs.getString("LocationName"),
                rs.getString("LocationType"),
                rs.getString("StorageType"),
                rs.getString("Unit"),
                rs.getInt("Capacity"),
                rs.getString("Status"),
                rs.getInt("WarehouseID")
            ), 
            locationID
        );
        return location;
    }
    

    @PostMapping("/create")
    public ResponseEntity<WarehouseLocation> createLocation(@RequestBody WarehouseLocationCreateRequest request) {
        String sql = """
            INSERT INTO Location (LocationName, LocationType, StorageType, Unit, Capacity, Status, WarehouseID)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            request.locationName(),
            request.locationType(),
            request.storageType(),
            request.unit(),
            request.capacity(),
            request.status(),
            request.warehouseID()
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        WarehouseLocation location = new WarehouseLocation(
            id,
            request.locationName(),
            request.locationType(),
            request.storageType(),
            request.unit(),
            request.capacity(),
            request.status(),
            request.warehouseID()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(location);
    }

    @GetMapping("/get-by-warehouse/{warehouseID}")
    public WarehouseLocation[] getLocationsByWarehouse(@PathVariable Integer warehouseID) {
        String sql = """
            SELECT * FROM Location
            WHERE WarehouseID = ?
        """;

        WarehouseLocation[] locations = jdbcTemplate.query(
            sql,
            new Object[]{warehouseID},
            (rs, rowNum) -> new WarehouseLocation(
                rs.getInt("LocationID"),
                rs.getString("LocationName"),
                rs.getString("LocationType"),
                rs.getString("StorageType"),
                rs.getString("Unit"),
                rs.getInt("Capacity"),
                rs.getString("Status"),
                rs.getInt("WarehouseID")
            )
        ).toArray(WarehouseLocation[]::new);

        return locations;
    }
}