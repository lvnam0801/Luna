package com.lvnam0801.Luna.Resource.Import.ImportProduct.Controller;

import com.lvnam0801.Luna.Resource.Import.ImportProduct.Representation.ImportProduct;
import com.lvnam0801.Luna.Resource.Import.ImportProduct.Representation.ImportProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/import-product")
public class ImportProductController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-by-receipt/{receiptID}")
    public ImportProduct[] getByReceipt(@PathVariable Integer receiptID) {
        String sql = "SELECT * FROM ImportProduct WHERE ReceiptID = ?";

        return jdbcTemplate.query(
            sql,
            new Object[]{receiptID},
            (rs, rowNum) -> new ImportProduct(
                rs.getInt("ImportProductID"),
                rs.getInt("ReceiptID"),
                rs.getInt("ProductID"),
                rs.getInt("Amount")
            )
        ).toArray(ImportProduct[]::new);
    }

    @PostMapping("/create")
    public ResponseEntity<ImportProduct> createImportProduct(@RequestBody ImportProductRequest request) {
        String sql = """
            INSERT INTO ImportProduct (ReceiptID, ProductID, Amount)
            VALUES (?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            request.receiptID(),
            request.productID(),
            request.amount()
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        ImportProduct product = new ImportProduct(
            id,
            request.receiptID(),
            request.productID(),
            request.amount()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}