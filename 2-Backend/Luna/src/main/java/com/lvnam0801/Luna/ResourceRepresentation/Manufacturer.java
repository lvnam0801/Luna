package com.lvnam0801.Luna.ResourceRepresentation;

public record Manufacturer(
    Integer manufacturerID, 
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
){}