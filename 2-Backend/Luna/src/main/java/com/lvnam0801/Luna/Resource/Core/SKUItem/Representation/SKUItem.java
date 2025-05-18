package com.lvnam0801.Luna.Resource.Core.SKUItem.Representation;

import java.sql.Date;

public record SKUItem(
    // SKIItem table
    Integer itemID, 
    Integer productID,
    String productCode,
    String SKU, 
    Integer quantity, 
    String status,
    
    // Product table
    String name, 
    String photoURL,
    String unitName, 
    String origin, 
    Long wholesalePrice, 
    Long retailPrice,
    
    // Category table get from product
    Integer categoryID,
    String categoryName,
    
    // Manufacturer table get from product
    Integer manufacturerID,
    String manufacturerName,
    
    // Location: from putaway, refer to Warehouse table and Location table (one putaway has one SKUItem)
    Integer warehouseID,
    String warehouseName,
    Integer locationID,
    String locationName,
    
    // From Import Receipt Line Item table get from Putaway
    Integer receiptLineItemID,
    Date expirationDate,
    String lotNumber
    // Join table Receipt Line Item
    
) {}
