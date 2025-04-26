package com.lvnam0801.Luna.Utility;

import org.springframework.jdbc.core.JdbcTemplate;

import java.time.Year;

public class SKUGenerator {

    public static String generateSKU(JdbcTemplate jdbcTemplate, Integer productID) {
        // Step 1: Get product name/code
        String productCode = jdbcTemplate.queryForObject(
            "SELECT REPLACE(UPPER(Name), ' ', '') FROM Product WHERE ProductID = ?",
            new Object[]{productID},
            String.class
        );

        // Step 2: Get count of SKUItems for this product (to generate sequence)
        Integer count = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM SKUItem WHERE ProductID = ?",
            new Object[]{productID},
            Integer.class
        );

        // Step 3: Build SKU
        String year = String.valueOf(Year.now().getValue());
        String sequence = String.format("%04d", count + 1);

        return "SKU-" + productCode + "-" + year + "-" + sequence;
    }
}