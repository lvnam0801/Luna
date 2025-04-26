package com.lvnam0801.Luna.Resource.Export.PickingTask.Representation;

import java.sql.Date;

public record PickingTask(
    Integer pickingTaskID,
    Integer orderLineItemID,
    Integer locationID,
    Integer pickedBy,
    Integer quantityPicked,
    Date pickedDate,
    String status
) {}