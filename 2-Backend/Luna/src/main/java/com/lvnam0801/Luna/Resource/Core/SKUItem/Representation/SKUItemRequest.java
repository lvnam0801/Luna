package com.lvnam0801.Luna.Resource.Core.SKUItem.Representation;

public record SKUItemRequest(
    Integer productID,
    Integer quantity,
    String status
) {}