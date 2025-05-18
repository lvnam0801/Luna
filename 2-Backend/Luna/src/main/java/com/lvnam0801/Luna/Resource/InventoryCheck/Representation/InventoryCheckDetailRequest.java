package com.lvnam0801.Luna.Resource.InventoryCheck.Representation;

public record InventoryCheckDetailRequest (
    Integer skuItemID,
    Integer systemQuantity,
    Integer actualQuantity,
    String quantityDifferenceReason,
    String note
){}