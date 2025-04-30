package com.lvnam0801.Luna.Resource.Core.WarehouseLocation.Representation;

public record WarehouseLocationCreateRequest(
    String locationName,
    String locationType,
    String storageType,
    String unit,
    Integer capacity,
    String status,
    Integer warehouseID
) {}