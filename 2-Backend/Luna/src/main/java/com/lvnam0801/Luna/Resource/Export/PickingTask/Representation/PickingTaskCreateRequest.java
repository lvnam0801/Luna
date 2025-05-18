package com.lvnam0801.Luna.Resource.Export.PickingTask.Representation;

import java.sql.Date;

public record PickingTaskCreateRequest(
    String pickingNumber,
    
    Integer orderID,
    Integer orderLineItemID,
    
    Integer skuItemID,
    Integer pickedQuantity,
    
    Integer warehouseID,
    Integer pickFromLocationID,
    
    String status,
    Date pickedDate
) {}