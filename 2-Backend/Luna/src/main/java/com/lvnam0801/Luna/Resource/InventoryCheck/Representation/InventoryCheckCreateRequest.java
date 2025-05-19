package com.lvnam0801.Luna.Resource.InventoryCheck.Representation;

import java.time.LocalDate;
import java.util.List;

public record InventoryCheckCreateRequest (
    String inventoryCheckNumber,
    Integer warehouseID,
    Integer checkedBy,
    LocalDate checkedDate,
    List<InventoryCheckDetailCreateRequest> details
){}