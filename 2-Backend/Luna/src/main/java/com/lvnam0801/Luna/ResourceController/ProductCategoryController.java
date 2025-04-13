package com.lvnam0801.Luna.ResourceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lvnam0801.Luna.ResourceRepresentation.ProductCategory;
import com.lvnam0801.Luna.ResourceRepresentation.ProductCategoryRequest;



@RestController
@RequestMapping("/api/product-category")
public class ProductCategoryController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-all")
    public ProductCategory[] getProductCategories() {
        String sql = """
            SELECT pc.CategoryID, pc.Name, pc.Description, pc.ParentID, pc.Status,
                COUNT(p.ProductID) AS ProductCount
            FROM ProductCategory pc
            LEFT JOIN Product p ON pc.CategoryID = p.CategoryID
            GROUP BY pc.CategoryID, pc.Name, pc.Description, pc.ParentID, pc.Status
            ORDER BY ProductCount DESC
        """;

        ProductCategory[] productCategories = jdbcTemplate.query(
            sql, 
            (rs, rowNum) -> new ProductCategory(
                rs.getInt("CategoryID"),
                rs.getString("Name"),
                rs.getString("Description"),
                rs.getObject("ParentID", Integer.class), // Handle NULL values correctly
                rs.getString("Status"),
                rs.getInt("ProductCount") // New field
            )
        ).toArray(ProductCategory[]::new);

        return productCategories;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategoryRequest request) {
        // SQL to insert a new ProductCategory
        String sql = """
            INSERT INTO ProductCategory (Name, Description, ParentID, Status)
            VALUES (?, ?, ?, ?)
        """;

        // Execute the update
        jdbcTemplate.update(sql, request.name(), request.description(), request.parentID(), request.status());

        // Retrieve the last inserted ID
        Integer categoryId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        // Construct and return the created ProductCategory
        ProductCategory newCategory = new ProductCategory(
            categoryId,
            request.name(),
            request.description(),
            request.parentID(),
            request.status(),
            0 // New category starts with 0 products
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }
}
