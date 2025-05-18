package com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation;

import java.math.BigDecimal;
import java.sql.Date;

public record ImportReceiptLineItemCreateRequest(
    Integer warehouseID,
    Integer receiptID,
    Integer productID,
    Integer receivedQuantity,
    String lotNumber,
    Date expirationDate,
    BigDecimal unitCost,
    String status
) {}