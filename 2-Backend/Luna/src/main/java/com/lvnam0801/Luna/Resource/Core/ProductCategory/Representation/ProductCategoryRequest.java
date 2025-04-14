package com.lvnam0801.Luna.Resource.Core.ProductCategory.Representation;

public record ProductCategoryRequest(
    String name,
    String description,
    Integer parentID,
    String status
) {}