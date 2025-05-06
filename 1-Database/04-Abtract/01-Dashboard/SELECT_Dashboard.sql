SELECT 
    p.ProductCode AS productCode,
    p.Name AS productName,
    SUM(eol.ExportedQuantity) AS unitsSold
FROM ExportOrderLineItem eol
JOIN Product p ON eol.ProductID = p.ProductID
GROUP BY p.ProductID, p.ProductCode, p.Name
ORDER BY unitsSold DESC
LIMIT 10;