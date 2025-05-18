SELECT
        i.InspectionID,
        i.InspectionNumber,
        i.ReceiptID,
        ih.ReceiptNumber AS ReceiptNumber,
        i.ReceiptLineItemID,
        il.LotNumber AS LotNumber,
        i.InspectedBy,
        ub.FullName AS InspectedByName,
        i.InspectionDate,
        i.WarehouseID,
        w.Name AS WarehouseName,
        i.InspectedLocationID,
        l.LocationName AS InspectedLocationName,
        i.Quantity,
        i.InspectionResult,
        i.Notes,
        i.Status,
        i.CreatedBy,
        uc.FullName AS CreatedByName,
        i.CreatedAt,
        i.UpdatedBy,
        uu.FullName AS UpdatedByName,
        i.UpdatedAt
    FROM QualityInspection i
    LEFT JOIN ImportReceiptHeader ih ON i.ReceiptID = ih.ReceiptID
    LEFT JOIN ImportReceiptLineItem il ON i.ReceiptLineItemID = il.ReceiptLineItemID
    LEFT JOIN User ub ON i.InspectedBy = ub.UserID
    LEFT JOIN Warehouse w ON i.WarehouseID = w.WarehouseID
    LEFT JOIN Location l ON i.InspectedLocationID = l.LocationID
    LEFT JOIN User uc ON i.CreatedBy = uc.UserID
    LEFT JOIN User uu ON i.UpdatedBy = uu.UserID
    WHERE i.ReceiptLineItemID = 1