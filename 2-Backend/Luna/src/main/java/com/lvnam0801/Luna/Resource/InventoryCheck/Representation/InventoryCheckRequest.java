package com.lvnam0801.Luna.Resource.InventoryCheck.Representation;

import java.util.List;

public record InventoryCheckRequest (
    Integer warehouseID,
    Integer checkedBy,
    List<InventoryCheckDetailRequest> details
){}