package com.lvnam0801.Luna.Resource.Export.Shipment.Representation;

import java.sql.Date;

public record ShipmentUpdateRequest(
    Integer carrierID,
    Integer shipFromLocationID,
    Integer shipToAddressID,
    Date shippedDate,
    Date expectedArrivalDate,
    Date actualArrivalDate,
    String shipmentStatus,
    String notes,
    String status
) {}