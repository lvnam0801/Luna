package com.lvnam0801.Luna.Resource.Export.Shipment.Representation;

import java.sql.Date;

public record ShipmentRequest(
    Integer orderID,
    Integer carrierID,
    Integer shippedBy,
    Date shipmentDate,
    String trackingNumber,
    Long shippingCost,
    String status
) {}