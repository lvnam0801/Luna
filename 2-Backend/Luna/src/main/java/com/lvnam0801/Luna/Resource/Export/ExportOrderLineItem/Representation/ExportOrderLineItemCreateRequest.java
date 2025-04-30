package com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation;

import java.math.BigDecimal;
import java.util.Date;

public record ExportOrderLineItemCreateRequest(
    Integer orderID,
    Integer productID,
    Integer exportedQuantity,
    String lotNumber,
    Date expirationDate,
    BigDecimal unitPrice,
    String status
) {}