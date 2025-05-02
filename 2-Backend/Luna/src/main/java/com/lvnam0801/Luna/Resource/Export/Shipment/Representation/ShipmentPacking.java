package com.lvnam0801.Luna.Resource.Export.Shipment.Representation;

import java.sql.Timestamp;

public record ShipmentPacking(
    Integer shipmentPackingID,
    Integer shipmentID,
    Integer packingID,
    Timestamp createdAt
) {}