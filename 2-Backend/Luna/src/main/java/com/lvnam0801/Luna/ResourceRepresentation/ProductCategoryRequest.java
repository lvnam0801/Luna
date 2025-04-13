package com.lvnam0801.Luna.ResourceRepresentation;

public record ProductCategoryRequest(
    String name,
    String description,
    Integer parentID,
    String status
) {}