package com.lvnam0801.Luna.Resource.Core.ProductDimension.Representation;

public record ProductDimension(
    String dimensionType,  // Example: "Width", "Height", "Weight"
    Double value,  // Example: 10.5
    String unit  // Example: "cm", "kg"
) {}