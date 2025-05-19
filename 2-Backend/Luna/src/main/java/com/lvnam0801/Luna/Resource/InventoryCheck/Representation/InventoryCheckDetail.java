package com.lvnam0801.Luna.Resource.InventoryCheck.Representation;

public record InventoryCheckDetail (
    Integer inventoryCheckDetailsID,
    Integer inventoryCheckID,
    
    Integer skuItemID,
    String sku,

    Integer locationID,
    String locationName,

    Integer systemQuantity,
    Integer actualQuantity,
    
    String quantityDifferenceReason,
    String note,
    String Status
){}