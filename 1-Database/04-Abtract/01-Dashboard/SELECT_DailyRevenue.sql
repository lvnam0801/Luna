-- Left join: all export days + matching import days
SELECT 
    DATE_FORMAT(e.day, '%Y-%m-%d') AS day,
    e.revenue,
    IFNULL(i.cost, 0) AS cost
FROM (
    SELECT DATE(eh.OrderDate) AS day,
           SUM(eol.ExportedQuantity * eol.UnitPrice) AS revenue
    FROM ExportOrderHeader eh
    JOIN ExportOrderLineItem eol ON eh.OrderID = eol.OrderID
    WHERE eh.Status = 'active'
    GROUP BY DATE(eh.OrderDate)
) e
LEFT JOIN (
    SELECT DATE(ih.ActualArrivalDate) AS day,
           SUM(il.ReceivedQuantity * il.UnitCost) AS cost
    FROM ImportReceiptHeader ih
    JOIN ImportReceiptLineItem il ON ih.ReceiptID = il.ReceiptID
    WHERE ih.Status = 'active'
    GROUP BY DATE(ih.ActualArrivalDate)
) i ON e.day = i.day

UNION

-- Right join fallback: import days with no matching export days
SELECT 
    DATE_FORMAT(i.day, '%Y-%m-%d') AS day,
    IFNULL(e.revenue, 0) AS revenue,
    i.cost
FROM (
    SELECT DATE(eh.OrderDate) AS day,
           SUM(eol.ExportedQuantity * eol.UnitPrice) AS revenue
    FROM ExportOrderHeader eh
    JOIN ExportOrderLineItem eol ON eh.OrderID = eol.OrderID
    WHERE eh.Status = 'active'
    GROUP BY DATE(eh.OrderDate)
) e
RIGHT JOIN (
    SELECT DATE(ih.ActualArrivalDate) AS day,
           SUM(il.ReceivedQuantity * il.UnitCost) AS cost
    FROM ImportReceiptHeader ih
    JOIN ImportReceiptLineItem il ON ih.ReceiptID = il.ReceiptID
    WHERE ih.Status = 'active'
    GROUP BY DATE(ih.ActualArrivalDate)
) i ON e.day = i.day

ORDER BY day ASC;