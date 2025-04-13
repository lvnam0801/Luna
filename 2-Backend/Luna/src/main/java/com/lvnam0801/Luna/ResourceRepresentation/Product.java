package com.lvnam0801.Luna.ResourceRepresentation;
import java.util.List;

public record Product(
    Integer productID,
    String name,
    String photoURL,
    String origin,
    Long wholesalePrice,
    Long retailPrice,
    String status,  // ENUM: 'Active', 'Inactive'
    String manufacturer,  // Manufacturer Name instead of ID
    String category,  // Category Name instead of ID
    List<ProductDimension> dimensions  // List of dimensions for the product
) {}