package com.lvnam0801.Luna.Resource.Export.Shipment.Representation;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.lvnam0801.Luna.Resource.Core.Address.Representation.Address;

public record Shipment(
    Integer shipmentID,
    String shipmentNumber,

    Integer orderID,
    String orderNumber,

    Integer carrierID,
    String carrierName,

    Integer shipFromLocationID,
    String shipFromLocationName,

    Integer shipToAddressID,
    Address shipToAddress,

    Date shippedDate,
    Date expectedArrivalDate,
    Date actualArrivalDate,

    String shipmentStatus,
    String notes,
    String status,

    Integer createdBy,
    String createdByName,
    Timestamp createdAt,

    Integer updatedBy,
    String updatedByName,
    Timestamp updatedAt,

    List<ShipmentPacking> shipmentPackings
) {}