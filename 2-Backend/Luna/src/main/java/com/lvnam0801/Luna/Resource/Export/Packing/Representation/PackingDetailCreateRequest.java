package com.lvnam0801.Luna.Resource.Export.Packing.Representation;

public record PackingDetailCreateRequest(
    Integer packingID,
    Integer skuItemID,
    Integer packedQuantity
) {}