package com.lvnam0801.Luna.Resource.Export.PickingTask.Representation;

import java.sql.Date;

public record PickingTaskUpdateRequest(
    Integer pickedQuantity,
    Integer pickFromLocationID,
    String status,
    Date pickedDate
) {}