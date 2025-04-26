package com.lvnam0801.Luna.Resource.Export.ExportProduct.Representation;

public record ExportProduct(
    Integer exportProductID,
    Integer orderID,
    Integer productID,
    Integer amount
) {}