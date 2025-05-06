SELECT 
    eh.OrderID,
    p.ContactName AS customerName,
    SUM(eol.ExportedQuantity * eol.UnitPrice) AS totalPrice,
    eh.OrderStatus AS status,
    eh.OrderDate
FROM ExportOrderHeader eh
JOIN Party p ON eh.CustomerID = p.PartyID
JOIN ExportOrderLineItem eol ON eh.OrderID = eol.OrderID
WHERE eh.Status = 'active'
GROUP BY eh.OrderID, p.ContactName, eh.OrderStatus, eh.OrderDate
ORDER BY eh.OrderDate DESC
LIMIT 5;