package com.lvnam0801.Luna.Resource.Export.ExportProduct.Controller;

import com.lvnam0801.Luna.Resource.Export.ExportProduct.Representation.ExportProduct;
import com.lvnam0801.Luna.Resource.Export.ExportProduct.Representation.ExportProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/export-product")
public class ExportProductController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-by-order/{orderID}")
    public ExportProduct[] getByOrder(@PathVariable Integer orderID) {
        String sql = "SELECT * FROM ExportProduct WHERE OrderID = ?";

        return jdbcTemplate.query(
            sql,
            new Object[]{orderID},
            (rs, rowNum) -> new ExportProduct(
                rs.getInt("ExportProductID"),
                rs.getInt("OrderID"),
                rs.getInt("ProductID"),
                rs.getInt("Amount")
            )
        ).toArray(ExportProduct[]::new);
    }

    @PostMapping("/create")
    public ResponseEntity<ExportProduct> createExportProduct(@RequestBody ExportProductRequest request) {
        String sql = """
            INSERT INTO ExportProduct (OrderID, ProductID, Amount)
            VALUES (?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            request.orderID(),
            request.productID(),
            request.amount()
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        ExportProduct exportProduct = new ExportProduct(
            id,
            request.orderID(),
            request.productID(),
            request.amount()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(exportProduct);
    }
}