-- SELECT * FROM ImportReceiptLineItem;

SELECT 
    li.ReceiptLineItemID,
    li.ReceiptID,
    li.ProductID,
    li.LineItemNumber,
    li.ReceivedQuantity,
    li.LotNumber,
    li.ExpirationDate,
    li.UnitCost,
    li.Status,
    li.CreatedBy,
    li.CreatedAt,
    li.UpdatedBy,
    li.UpdatedAt,
    p.Name AS ProductName,
    cu.FullName AS CreatedByName,
    uu.FullName AS UpdatedByName
FROM ImportReceiptLineItem li
LEFT JOIN Product p ON li.ProductID = p.ProductID
LEFT JOIN User cu ON li.CreatedBy = cu.UserID
LEFT JOIN User uu ON li.UpdatedBy = uu.UserID
WHERE li.ReceiptID = 1