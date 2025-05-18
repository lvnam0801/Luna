package com.lvnam0801.Luna.Resource.Export.Packing.Representation;

import java.sql.Date;

public record PackingCreateRequest(
    String packingNumber,
    Integer orderID,
    Integer orderLineItemID,
    Integer warehouseID,
    Integer packToLocationID,
    String status,
    Date packedDate
) {}