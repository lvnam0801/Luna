package com.lvnam0801.Luna.Resource.Export.Packing.Representation;

public record PackingDetail(
    Integer packingDetailID,
    Integer packingID,
    Integer skuItemID,
    String sku,
    String productName,
    Integer packedQuantity
) {}