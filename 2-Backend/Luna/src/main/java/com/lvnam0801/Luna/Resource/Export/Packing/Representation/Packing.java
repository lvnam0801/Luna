package com.lvnam0801.Luna.Resource.Export.Packing.Representation;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public record Packing(
    Integer packingID,
    String packingNumber,
    Integer orderID,
    String orderNumber,
    
    Integer orderLineItemID,
    String lotNumber,

    Integer warehouseID,
    String warehouseName,

    Integer packToLocationID,
    String packToLocationName,
    
    String status,
    Integer packedBy,
    String packedByName,
    Date packedDate,
    Integer createdBy,
    String createdByName,
    Timestamp createdAt,
    Integer updatedBy,
    String updatedByName,
    Timestamp updatedAt,
    List<PackingDetail> skuItems
) {}