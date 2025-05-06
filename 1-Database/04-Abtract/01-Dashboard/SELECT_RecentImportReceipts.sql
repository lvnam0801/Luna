SELECT 
    ih.ReceiptID,
    p.ContactName AS providerName,
    SUM(il.ReceivedQuantity * il.UnitCost) AS totalPrice,
    ih.ReceiptStatus AS status,
    ih.ActualArrivalDate AS date
FROM ImportReceiptHeader ih
JOIN Party p ON ih.SupplierID = p.PartyID
JOIN ImportReceiptLineItem il ON ih.ReceiptID = il.ReceiptID
WHERE ih.Status = 'active'
GROUP BY ih.ReceiptID, p.ContactName, ih.ReceiptStatus, ih.ActualArrivalDate
ORDER BY ih.ActualArrivalDate DESC
LIMIT 5;