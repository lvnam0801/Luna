package com.lvnam0801.Luna.Resource.InventoryCheck.Representation;

public record InventoryCheckDetailCreateRequest (
    Integer skuItemID,
    Integer locationID,
    Integer systemQuantity,
    Integer actualQuantity,
    String quantityDifferenceReason,
    String note
){}