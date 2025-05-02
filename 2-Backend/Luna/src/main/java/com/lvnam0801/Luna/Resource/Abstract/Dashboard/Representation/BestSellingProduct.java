package com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation;

public record BestSellingProduct(
    String productCode,
    String productName,
    int unitsSold
) {}
