package com.lvnam0801.Luna.Resource.Export.ExportProduct.Representation;

public record ExportProductRequest(
    Integer orderID,
    Integer productID,
    Integer amount
) {}