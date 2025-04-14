package com.lvnam0801.Luna.Resource.Core.ProductDimension.Representation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDimensionRequest {
    private String dimensionType; // Example: "Length", "Width", "Weight"
    private double value; // Example: 10.5
    private String unit; // Example: "cm", "kg"
}