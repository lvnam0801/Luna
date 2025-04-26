package com.lvnam0801.Luna.Resource.Import.ImportProduct.Representation;

public record ImportProductRequest(
    Integer receiptID,
    Integer productID,
    Integer amount
) {}