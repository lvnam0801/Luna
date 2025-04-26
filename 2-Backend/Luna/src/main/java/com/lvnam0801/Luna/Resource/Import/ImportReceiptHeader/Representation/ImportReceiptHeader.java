package com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation;

import java.sql.Timestamp;
import java.sql.Date;

public record ImportReceiptHeader(
    Integer receiptID,
    String receiptNumber,
    String asnNumber,
    String poNumber,
    Integer originLocationID,
    Date expectedArrivalDate,
    Date actualArrivalDate,
    String receivingDock,
    String receiptStatus,
    String notes,
    Integer carrierID,
    Integer supplierID,
    Integer warehouseID,
    Integer createdBy,
    Timestamp createdDate
) {}