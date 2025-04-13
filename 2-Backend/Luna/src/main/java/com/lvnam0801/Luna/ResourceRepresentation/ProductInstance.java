package com.lvnam0801.Luna.ResourceRepresentation;

import java.time.LocalTime;
import java.util.List;

public record ProductInstance(
    Integer instanceID, 
    String SKU, 
    Integer quantity, 
    LocalTime expiredDate, 
    String name, 
    String photoURL, 
    String origin, 
    Long wholesalePrice, 
    Long retailPrice, 
    String warehouseName,
    String manufacturerName,
    String categoryName,
    String status,
    List<ProductDimension> dimensions
) {}
