package com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation;

import java.sql.Date;

public record QualityInspectionCreateRequest(
    Integer receiptLineItemID,
    Integer inspectedBy,
    Date inspectionDate,
    Integer inspectedLocationID,
    Integer quantity,
    String inspectionResult,
    String notes,
    String status
) {}