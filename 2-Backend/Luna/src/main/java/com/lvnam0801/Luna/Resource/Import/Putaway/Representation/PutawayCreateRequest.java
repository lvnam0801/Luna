package com.lvnam0801.Luna.Resource.Import.Putaway.Representation;

import java.sql.Date;

public record PutawayCreateRequest(
    Integer receiptID,
    Integer receiptLineItemID,
    Integer warehouseID,
    Integer putawayAtLocationID,
    Integer quantity,
    String putawayResult, // 'stored' or 'quarantined'
    String status,        // 'pending', 'completed', 'cancelled'
    Date putawayDate
) {}