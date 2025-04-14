package com.lvnam0801.Luna.Resource.Core.ProductCategory.Representation;

public record ProductCategory(
    Integer categoryID,
    String name,
    String description,
    Integer parentID,
    String status,
    Integer productCount
) {}