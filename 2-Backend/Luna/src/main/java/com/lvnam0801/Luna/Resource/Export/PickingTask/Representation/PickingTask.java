package com.lvnam0801.Luna.Resource.Export.PickingTask.Representation;

import java.sql.Date;
import java.sql.Timestamp;

public record PickingTask(
    Integer pickingTaskID,
    String pickingNumber,
    Integer orderLineItemID,

    Integer skuItemID,
    String sku,
    Integer pickedQuantity,

    Integer pickFromLocationID,
    String pickFromLocationName,

    // Descriptive info
    String status,
    
    Integer pickedBy,
    String pickedByName,
    Date pickedDate,

    Integer createdBy,
    String createdByName,
    Timestamp createdAt,

    Integer updatedBy,
    String updatedByName,
    Timestamp updatedAt
) {}