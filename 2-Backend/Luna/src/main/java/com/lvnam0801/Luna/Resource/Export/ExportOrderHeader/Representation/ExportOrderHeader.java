package com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation;

import java.sql.Date;
import java.sql.Timestamp;

public record ExportOrderHeader(
    Integer orderID,
    String orderNumber,
    Integer customerID,
    Integer carrierID,
    Integer warehouseID,
    Integer shippingAddressID,
    Date orderDate,
    Date requestedDeliveryDate,
    String shippingMethod,
    String orderStatus,
    String notes,
    Integer createdBy,
    Timestamp createdDate
) {}