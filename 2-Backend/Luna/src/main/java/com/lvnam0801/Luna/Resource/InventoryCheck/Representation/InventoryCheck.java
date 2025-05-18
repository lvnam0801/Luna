package com.lvnam0801.Luna.Resource.InventoryCheck.Representation;

import java.time.LocalDateTime;
import java.util.List;

public record InventoryCheck (
    Integer inventoryCheckID,
    
    Integer warehouseID,
    String warehouseName,
    
    Integer checkedBy,
    String checkedByName,

    LocalDateTime checkedDate,
    
    Integer createdBy,
    String createdByName,
    LocalDateTime createdAt,
    
    Integer updatedBy,
    String updatedByName,
    LocalDateTime updatedAt,

    List<InventoryCheckDetail> details
){}