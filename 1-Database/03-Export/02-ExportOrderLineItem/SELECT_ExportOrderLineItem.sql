SELECT e.*, 
    p.Name,
    u1.FullName AS CreatedByName, 
    u2.FullName AS UpdatedByName 
FROM ExportOrderLineItem e
LEFT JOIN Product p ON e.ProductID = p.ProductID
LEFT JOIN User u1 ON e.CreatedBy = u1.UserID
LEFT JOIN User u2 ON e.UpdatedBy = u2.UserID
WHERE e.OrderLineItemID = 1