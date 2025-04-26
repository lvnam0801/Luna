package com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation;

import java.sql.Date;
import java.sql.Timestamp;

public record QualityInspection(
    Integer inspectionID,
    String inspectionNumber,
    Integer receiptLineItemID,
    Integer inspectedBy,
    Date inspectionDate,
    Integer inspectedLocationID,
    Integer quantity,
    String inspectionResult,
    String notes,
    String status,
    Integer createdBy,
    Timestamp createdAt,
    Integer updatedBy,
    Timestamp updatedAt
) {}