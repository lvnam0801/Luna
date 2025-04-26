package com.lvnam0801.Luna.Resource.Core.Warehouse.Representation;

public record WarehouseRequest(
    String name,
    String phone,
    String email,
    String streetAddress,
    String city,
    String stateProvince,
    String postalCode,
    String country,
    String status
) {}