package com.lvnam0801.Luna.Resource.Core.WarehouseLocation.Representation;

public record WarehouseLocation(
    Integer locationID,
    String locationName,
    String locationType,
    String storageType,
    String unit,
    Integer capacity,
    String status,
    Integer warehouseID
) {}