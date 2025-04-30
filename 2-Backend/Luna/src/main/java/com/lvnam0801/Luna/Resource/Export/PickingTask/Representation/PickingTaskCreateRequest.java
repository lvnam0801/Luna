package com.lvnam0801.Luna.Resource.Export.PickingTask.Representation;

import java.sql.Date;

public record PickingTaskCreateRequest(
    String pickingNumber,
    Integer orderLineItemID,
    Integer skuItemID,
    Integer pickedQuantity,
    Integer pickFromLocationID,
    String status,
    Date pickedDate
) {}