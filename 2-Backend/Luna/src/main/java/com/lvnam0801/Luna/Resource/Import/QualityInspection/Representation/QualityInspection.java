package com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation;

import java.sql.Date;
import java.sql.Timestamp;

public record QualityInspection(
    Integer inspectionID,
    String inspectionNumber,
    Integer receiptLineItemID,
    Integer inspectedBy,
    String inspectedByName, // Get from user table
    Date inspectionDate,
    Integer inspectedLocationID,
    String inspectedLocationName, // Get from location table
    Integer quantity,
    String inspectionResult,
    String notes,
    String status,
    Integer createdBy,
    String createdByName, // Get from user table
    Timestamp createdAt,
    Integer updatedBy,
    String updatedByName, // Get from user table
    Timestamp updatedAt
) {}