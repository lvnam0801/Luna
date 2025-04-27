package com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation;

import java.sql.Timestamp;

import com.lvnam0801.Luna.Resource.Core.Address.Representation.Address;

import java.sql.Date;

public record ImportReceiptHeader(
    Integer receiptID,
    String receiptNumber,
    String asnNumber,
    String poNumber,
    
    // From Address table
    Integer originLocationID, // This link to address table to explain where the product come from
    Address originLocation,

    Date expectedArrivalDate,
    Date actualArrivalDate,
    String receiptStatus,
    String notes,
    
    // From Party table
    Integer carrierID,
    String carrierName,
    Integer supplierID,
    String supplierName,
    
    // From warehouse table
    Integer warehouseID,
    String warehouseName,
    // From location table
    Integer receivingDockID,
    String receivingDockName,


    Integer createdBy,
    Timestamp createdAt,
    Timestamp updatedAt
) {}