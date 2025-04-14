package com.lvnam0801.Luna.Resource.Core.Product.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.lvnam0801.Luna.Resource.Core.Product.Representation.Product;
import com.lvnam0801.Luna.Resource.Core.Product.Representation.ProductCreateRequest;
import com.lvnam0801.Luna.Resource.Core.ProductDimension.Representation.ProductDimension;
import com.lvnam0801.Luna.Resource.Core.ProductDimension.Representation.ProductDimensionRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-all")
    public List<Product> getProducts() {
        // SQL Query to fetch products along with their dimensions
        String sql = """
            SELECT p.ProductID, p.Name, p.PhotoURL, p.Origin, p.WholesalePrice, p.RetailPrice, p.Status, 
                m.Name AS Manufacturer, c.Name AS Category, 
                d.DimensionType, d.Value, d.Unit
            FROM Product p
            JOIN Manufacturer m ON p.ManufacturerID = m.ManufacturerID
            JOIN ProductCategory c ON p.CategoryID = c.CategoryID
            LEFT JOIN ProductDimensions d ON p.ProductID = d.ProductID
            ORDER BY p.ProductID
        """;

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        Map<Integer, Product> productMap = new LinkedHashMap<>();

        for (Map<String, Object> row : rows) {
            Integer productID = (Integer) row.get("ProductID");

            // If product is not already in the map, create a new one
            productMap.putIfAbsent(productID, new Product(
                productID,
                (String) row.get("Name"),
                (String) row.get("PhotoURL"),
                (String) row.get("Origin"),
                (Long) row.get("WholesalePrice"),
                (Long) row.get("RetailPrice"),
                (String) row.get("Status"),
                (String) row.get("Manufacturer"),
                (String) row.get("Category"),
                new ArrayList<>()  // Initialize empty dimensions list
            ));

            // Add dimensions if they exist
            if (row.get("DimensionType") != null) {
                ProductDimension dimension = new ProductDimension(
                    (String) row.get("DimensionType"),
                    ((Number) row.get("Value")).doubleValue(),
                    (String) row.get("Unit")
                );
                productMap.get(productID).dimensions().add(dimension);
            }
        }

        return new ArrayList<>(productMap.values());
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody ProductCreateRequest productRequest) {
        // SQL to insert a new Product
        String productSql = """
            INSERT INTO Product (Name, PhotoURL, Origin, WholesalePrice, RetailPrice, Status, ManufacturerID, CategoryID)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        // Insert the Product
        jdbcTemplate.update(productSql, 
            productRequest.getName(),
            productRequest.getPhotoURL(),
            productRequest.getOrigin(),
            productRequest.getWholesalePrice(),
            productRequest.getRetailPrice(),
            productRequest.getStatus(),
            productRequest.getManufacturerID(),
            productRequest.getCategoryID()
        );

        // Get the generated ProductID
        Integer productId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        // Insert Dimensions if provided
        if (productRequest.getDimensions() != null && !productRequest.getDimensions().isEmpty()) {
            String dimensionSql = """
                INSERT INTO ProductDimensions (ProductID, DimensionType, Value, Unit)
                VALUES (?, ?, ?, ?)
            """;
            
            for (ProductDimensionRequest dimension : productRequest.getDimensions()) {
                jdbcTemplate.update(dimensionSql, 
                    productId,
                    dimension.getDimensionType(),
                    dimension.getValue(),
                    dimension.getUnit()
                );
            }
        }

        // Response with the created Product ID
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Product created successfully!");
        response.put("productId", productId);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
