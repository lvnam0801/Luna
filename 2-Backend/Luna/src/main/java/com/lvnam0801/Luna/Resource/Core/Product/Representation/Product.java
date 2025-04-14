package com.lvnam0801.Luna.Resource.Core.Product.Representation;
import java.util.List;

import com.lvnam0801.Luna.Resource.Core.ProductDimension.Representation.ProductDimension;

public record Product(
    Integer productID,
    String name,
    String photoURL,
    String origin,
    Long wholesalePrice,
    Long retailPrice,
    String status,  // ENUM: 'active', 'inactive'
    String manufacturer,  // Manufacturer Name instead of ID
    String category,  // Category Name instead of ID
    List<ProductDimension> dimensions  // List of dimensions for the product
) {}