package com.lvnam0801.Luna.Resource.Export.Shipment.Controller;

import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.Shipment;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipment")
public class ShipmentController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-by-order/{orderID}")
    public Shipment[] getByOrder(@PathVariable Integer orderID) {
        String sql = "SELECT * FROM Shipment WHERE OrderID = ?";

        return jdbcTemplate.query(
            sql,
            new Object[]{orderID},
            (rs, rowNum) -> new Shipment(
                rs.getInt("ShipmentID"),
                rs.getInt("OrderID"),
                rs.getInt("CarrierID"),
                rs.getObject("ShippedBy", Integer.class),
                rs.getDate("ShipmentDate"),
                rs.getString("TrackingNumber"),
                rs.getLong("ShippingCost"),
                rs.getString("Status")
            )
        ).toArray(Shipment[]::new);
    }

    @PostMapping("/create")
    public ResponseEntity<Shipment> createShipment(@RequestBody ShipmentRequest request) {
        String sql = """
            INSERT INTO Shipment (OrderID, CarrierID, ShippedBy, ShipmentDate, TrackingNumber, ShippingCost, Status)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            request.orderID(),
            request.carrierID(),
            request.shippedBy(),
            request.shipmentDate(),
            request.trackingNumber(),
            request.shippingCost(),
            request.status()
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        Shipment shipment = new Shipment(
            id,
            request.orderID(),
            request.carrierID(),
            request.shippedBy(),
            request.shipmentDate(),
            request.trackingNumber(),
            request.shippingCost(),
            request.status()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(shipment);
    }
}