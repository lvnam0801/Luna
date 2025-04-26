package com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Controller;

import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation.ExportOrderHeader;
import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation.ExportOrderHeaderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/export-order")
public class ExportOrderHeaderController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-all")
    public ExportOrderHeader[] getExportOrders() {
        String sql = "SELECT * FROM ExportOrderHeader";

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new ExportOrderHeader(
                rs.getInt("OrderID"),
                rs.getString("OrderNumber"),
                rs.getInt("CustomerID"),
                rs.getObject("CarrierID", Integer.class),
                rs.getInt("WarehouseID"),
                rs.getInt("ShippingAddressID"),
                rs.getDate("OrderDate"),
                rs.getDate("RequestedDeliveryDate"),
                rs.getString("ShippingMethod"),
                rs.getString("OrderStatus"),
                rs.getString("Notes"),
                rs.getObject("CreatedBy", Integer.class),
                rs.getTimestamp("CreatedDate")
            )
        ).toArray(ExportOrderHeader[]::new);
    }

    @PostMapping("/create")
    public ResponseEntity<ExportOrderHeader> createOrder(@RequestBody ExportOrderHeaderRequest request) {
        String sql = """
            INSERT INTO ExportOrderHeader (
                OrderNumber, CustomerID, CarrierID, WarehouseID, ShippingAddressID,
                OrderDate, RequestedDeliveryDate, ShippingMethod, OrderStatus,
                Notes, CreatedBy
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            request.orderNumber(),
            request.customerID(),
            request.carrierID(),
            request.warehouseID(),
            request.shippingAddressID(),
            request.orderDate(),
            request.requestedDeliveryDate(),
            request.shippingMethod(),
            request.orderStatus(),
            request.notes(),
            request.createdBy()
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        ExportOrderHeader newOrder = new ExportOrderHeader(
            id,
            request.orderNumber(),
            request.customerID(),
            request.carrierID(),
            request.warehouseID(),
            request.shippingAddressID(),
            request.orderDate(),
            request.requestedDeliveryDate(),
            request.shippingMethod(),
            request.orderStatus(),
            request.notes(),
            request.createdBy(),
            null
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }
}