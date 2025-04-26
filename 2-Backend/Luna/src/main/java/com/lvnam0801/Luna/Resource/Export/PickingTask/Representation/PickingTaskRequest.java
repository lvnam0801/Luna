package com.lvnam0801.Luna.Resource.Export.PickingTask.Representation;

import java.sql.Date;

public record PickingTaskRequest(
    Integer orderLineItemID,
    Integer locationID,
    Integer pickedBy,
    Integer quantityPicked,
    Date pickedDate,
    String status
) {}