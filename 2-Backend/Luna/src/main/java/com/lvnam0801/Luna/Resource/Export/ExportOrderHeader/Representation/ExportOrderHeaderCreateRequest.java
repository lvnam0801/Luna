package com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation;

import java.sql.Date;

public record ExportOrderHeaderCreateRequest(
    String orderNumber,

    // From Party table
    Integer customerID,
    Integer carrierID,

    // From Warehouse table
    Integer warehouseID,

    // From Address table
    Integer shippingAddressID,

    String exportPurpose,
    Date orderDate,
    Date requestedDeliveryDate,
    String shippingMethod,
    String orderStatus,
    String notes,
    String status,

    // Direct transition from Import Receipt (nullable)
    Integer receiptID
) {}