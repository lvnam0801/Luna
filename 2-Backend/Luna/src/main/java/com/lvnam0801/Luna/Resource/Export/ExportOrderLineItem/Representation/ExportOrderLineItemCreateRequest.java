package com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation;

import java.math.BigDecimal;
import java.util.Date;

public record ExportOrderLineItemCreateRequest(
    Integer warehouseID,
    Integer orderID,
    Integer productID,
    
    String SKU,
    Integer exportedQuantity,

    String lotNumber,
    Date expirationDate,
    
    BigDecimal unitPrice,
    String status
) {}