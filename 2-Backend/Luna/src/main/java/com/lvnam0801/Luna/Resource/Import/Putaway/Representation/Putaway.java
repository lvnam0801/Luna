package com.lvnam0801.Luna.Resource.Import.Putaway.Representation;

import java.sql.Date;
import java.sql.Timestamp;

public record Putaway(
    Integer putawayID,
    String putawayNumber,
    Integer receiptLineItemID,
    Integer putawayAtLocationID,
    String putawayAtLocationName, // get from location
    Integer skuItemID,
    String SKU,         // from SKUItem table
    Integer quantity,
    String putawayResult,      // 'stored' or 'quarantined'
    String status,             // 'pending', 'completed', 'cancelled'
    Integer putawayBy,
    String putawayByName, // from user table
    Date putawayDate,

    Integer createdBy,
    String createdByName, // from user table
    Timestamp createdAt,
    
    Integer updatedBy,
    String updatedByName, // from user table
    Timestamp updatedAt
) {}