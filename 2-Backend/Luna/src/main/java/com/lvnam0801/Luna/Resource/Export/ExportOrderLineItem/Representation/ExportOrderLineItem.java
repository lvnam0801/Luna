package com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

// Representation class
public record ExportOrderLineItem(
    Integer orderLineItemID,
    Integer orderID,

    Integer productID,
    String productName,

    String lineItemNumber,
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