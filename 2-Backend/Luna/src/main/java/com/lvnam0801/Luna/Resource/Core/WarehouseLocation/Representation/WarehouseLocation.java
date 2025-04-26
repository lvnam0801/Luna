package com.lvnam0801.Luna.Resource.Core.WarehouseLocation.Representation;

public record WarehouseLocation(
    Integer locationID,
    String locationType,
    String value,
    String unit,
    String status,
    Integer warehouseID
) {}