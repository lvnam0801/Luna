package com.lvnam0801.Luna.Resource.Core.Manufacturer.Representation;

public record ManufacturerRequest(
    String name,
    String logoURL,
    String streetAddress,
    String city,
    String stateProvince,
    String postalCode,
    String country,
    String phone,
    String email,
    String website,
    String status
) {}