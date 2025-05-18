package com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

// Representation class
public record ExportOrderLineItem(
    Integer orderLineItemID,

    Integer warehouseID,
    String warehouseName,

    Integer orderID,
    String orderNumber,

    Integer productID,
    String productName,

    String lineItemNumber,
    
    String SKU,
    Integer exportedQuantity,
    String lotNumber,
    Date expirationDate,
    BigDecimal unitPrice,
    String status,
    
    Integer createdBy,
    String createdByName,
    Timestamp createdAt,

    Integer updatedBy,
    String updatedByName,
    Timestamp updatedAt
) {}