package com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation;

import java.sql.Date;

public record ExportOrderLineItemRequest(
    Integer orderID,
    Integer itemID,
    Integer lineItemNumber,
    Integer orderedQuantity,
    Integer shippedQuantity,
    Long unitPrice,
    Date requestedDeliveryDate,
    String notes,
    String status
) {}