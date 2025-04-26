package com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public record ImportReceiptLineItemResponse(
    Integer receiptLineItemID,
    Integer receiptID,
    Integer productID,
    Integer lineItemNumber,
    Integer expectedQuantity,
    Integer receivedQuantity,
    Integer quantityDiscrepancy,
    String discrepancyReasonCode,
    String lotNumber,
    String serialNumber,
    Date expirationDate,
    BigDecimal unitCost,
    String status,
    Integer createdBy,
    String createdByName,
    Timestamp createdDate,
    Integer updatedBy,
    String updatedByName,
    Timestamp updatedDate
) {}
