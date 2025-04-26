package com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Controller;

import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItem;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/export-order-line")
public class ExportOrderLineItemController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-by-order/{orderID}")
    public ExportOrderLineItem[] getByOrder(@PathVariable Integer orderID) {
        String sql = "SELECT * FROM ExportOrderLineItem WHERE OrderID = ?";

        return jdbcTemplate.query(
            sql,
            new Object[]{orderID},
            (rs, rowNum) -> new ExportOrderLineItem(
                rs.getInt("OrderLineItemID"),
                rs.getInt("OrderID"),
                rs.getInt("ItemID"),
                rs.getInt("LineItemNumber"),
                rs.getInt("OrderedQuantity"),
                rs.getInt("ShippedQuantity"),
                rs.getLong("UnitPrice"),
                rs.getDate("RequestedDeliveryDate"),
                rs.getString("Notes"),
                rs.getString("Status")
            )
        ).toArray(ExportOrderLineItem[]::new);
    }

    @PostMapping("/create")
    public ResponseEntity<ExportOrderLineItem> createLine(@RequestBody ExportOrderLineItemRequest request) {
        String sql = """
            INSERT INTO ExportOrderLineItem (
                OrderID, ItemID, LineItemNumber, OrderedQuantity, ShippedQuantity,
                UnitPrice, RequestedDeliveryDate, Notes, Status
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            request.orderID(),
            request.itemID(),
            request.lineItemNumber(),
            request.orderedQuantity(),
            request.shippedQuantity(),
            request.unitPrice(),
            request.requestedDeliveryDate(),
            request.notes(),
            request.status()
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        ExportOrderLineItem line = new ExportOrderLineItem(
            id,
            request.orderID(),
            request.itemID(),
            request.lineItemNumber(),
            request.orderedQuantity(),
            request.shippedQuantity(),
            request.unitPrice(),
            request.requestedDeliveryDate(),
            request.notes(),
            request.status()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(line);
    }
}