package com.lvnam0801.Luna.Resource.Core.Party.Representation;

public record PartyRequest(
    String partyType,
    String contactName,
    String phone,
    String email,
    String note,
    String status,
    String streetAddress,
    String city,
    String stateProvince,
    String postalCode,
    String country
) {}