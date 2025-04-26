package com.lvnam0801.Luna.Resource.Import.Putaway.Representation;

import java.sql.Date;
import java.sql.Timestamp;

public record Putaway(
    Integer putawayID,
    String putawayNumber,
    Integer receiptLineItemID,
    Integer putawayAtLocation, // previously called locationID
    Integer skuItemID,
    Integer quantity,
    String putawayResult,      // 'stored' or 'quarantined'
    String status,             // 'pending', 'completed', 'cancelled'
    Integer putawayBy,
    Date putawayDate,
    Integer createdBy,
    Timestamp createdAt,
    Integer updatedBy,
    Timestamp updatedAt
) {}