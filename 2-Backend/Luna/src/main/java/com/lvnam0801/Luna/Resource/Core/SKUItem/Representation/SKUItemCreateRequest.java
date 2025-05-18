package com.lvnam0801.Luna.Resource.Core.SKUItem.Representation;

public record SKUItemCreateRequest(
    Integer quantity,
    Integer productID,
    Integer receiptLineItemID,
    Integer warehouseID,
    Integer locationID,
    String status
) {}