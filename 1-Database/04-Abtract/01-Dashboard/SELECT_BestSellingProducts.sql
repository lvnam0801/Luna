-- SELECT
--     p.ProductCode AS productCode,
--     p.Name AS productName,
--     SUM(eol.ExportedQuantity) AS unitsSold
-- FROM ExportOrderLineItem eol
-- JOIN Product p ON eol.ProductID = p.ProductID
-- JOIN ExportOrderHeader eoh ON eoh.OrderID = eol.OrderID
-- WHERE eoh.OrderDate BETWEEN 2025-04-01 AND 2025-05-07
-- GROUP BY p.ProductID, p.ProductCode, p.Name
-- ORDER BY unitsSold DESC
-- LIMIT 10

-- SELECT
--     p.ProductCode AS productCode,
--     p.Name AS productName,
--     SUM(eol.ExportedQuantity) AS unitsSold
-- FROM ExportOrderLineItem eol
-- JOIN ExportOrderHeader eh ON eol.OrderID = eh.OrderID
-- JOIN Product p ON eol.ProductID = p.ProductID
-- WHERE eh.OrderDate BETWEEN '2025-04-01' AND '2025-05-30'
-- GROUP BY p.ProductID, p.ProductCode, p.Name
-- ORDER BY unitsSold DESC
-- LIMIT 10
SELECT 
    p.ProductCode AS productCode,
    p.Name AS productName,
    SUM(eol.ExportedQuantity) AS unitsSold
FROM ExportOrderLineItem eol
JOIN ExportOrderHeader eh ON eol.OrderID = eh.OrderID
JOIN Product p ON eol.ProductID = p.ProductID
WHERE eh.ExportPurpose='sale'
GROUP BY p.ProductID, p.ProductCode, p.Name
ORDER BY unitsSold DESC
LIMIT 10