package com.lvnam0801.Luna.Resource.Core.Address.Representation;

public record AddressRequest(
    String streetAddress,
    String city,
    String stateProvince,
    String postalCode,
    String country
) {}