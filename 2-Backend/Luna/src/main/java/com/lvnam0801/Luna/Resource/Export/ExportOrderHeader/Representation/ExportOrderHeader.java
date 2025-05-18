package com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation;

import java.sql.Date;
import java.sql.Timestamp;

import com.lvnam0801.Luna.Resource.Core.Address.Representation.Address;

public record ExportOrderHeader(
    Integer orderID,
    String orderNumber,

    // From Party table
    Integer customerID,
    String customerName,
    Integer carrierID,
    String carrierName,

    // From Warehouse table
    Integer warehouseID,
    String warehouseName,

    // From Address table
    Integer shippingAddressID,
    Address shippingAddress,

    String exportPurpose,
    Date orderDate,
    Date requestedDeliveryDate,
    String shippingMethod,
    String orderStatus,
    String notes,
    String status,

    // Direct transition from Import Receipt (can be null)
    Integer receiptID,

    Integer createdBy,
    String createdByName,
    Integer updatedBy,
    String updatedByName,
    Timestamp createdAt,
    Timestamp updatedAt
) {}