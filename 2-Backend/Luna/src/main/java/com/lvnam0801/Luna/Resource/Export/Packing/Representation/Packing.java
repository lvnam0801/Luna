package com.lvnam0801.Luna.Resource.Export.Packing.Representation;

import java.sql.Date;
import java.sql.Timestamp;

public record Packing(
    Integer packingID,
    String packingNumber,
    Integer orderID,
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
    Timestamp updatedAt
) {}