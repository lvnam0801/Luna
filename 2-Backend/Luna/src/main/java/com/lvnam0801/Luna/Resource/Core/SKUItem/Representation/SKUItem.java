package com.lvnam0801.Luna.Resource.Core.SKUItem.Representation;

import java.time.LocalTime;
import java.util.List;

import com.lvnam0801.Luna.Resource.Core.ProductDimension.Representation.ProductDimension;

public record SKUItem(
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
