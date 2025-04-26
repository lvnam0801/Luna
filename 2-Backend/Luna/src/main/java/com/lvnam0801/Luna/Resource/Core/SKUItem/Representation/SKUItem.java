package com.lvnam0801.Luna.Resource.Core.SKUItem.Representation;

import java.time.LocalTime;

public record SKUItem(
    Integer itemID, 
    String SKU, 
    Integer quantity, 
    LocalTime expiredDate,
    String name, 
    String photoURL, 
    String origin, 
    Long wholesalePrice, 
    Long retailPrice,
    String manufacturerName,
    String categoryName,
    String status
) {}
