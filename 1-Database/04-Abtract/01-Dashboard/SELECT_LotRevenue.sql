SELECT
    eol.LotNumber,
    eol.ProductID,
    p.Name AS productName,
    SUM(eol.ExportedQuantity) AS totalExportedQty,
    eol.UnitPrice,

    -- Pull the actual import cost from the first non-zero cost import with same LotNumber
    (
        SELECT irl.UnitCost
        FROM ImportReceiptLineItem irl
        WHERE irl.LotNumber = eol.LotNumber
        -- ORDER BY 
        --     CASE WHEN irl.UnitCost IS NOT NULL AND irl.UnitCost > 0 THEN 0 ELSE 1 END,
        --     irl.CreatedAt ASC
        -- LIMIT 1
    ) AS unitCost,

    -- Total sales revenue
    SUM(eol.ExportedQuantity * eol.UnitPrice) AS totalSales,

    -- Total cost using export quantity × actual cost
    SUM(
        eol.ExportedQuantity * (
            SELECT IFNULL(irl.UnitCost, 0)
            FROM ImportReceiptLineItem irl
            WHERE irl.LotNumber = eol.LotNumber
              AND irl.UnitCost IS NOT NULL
              AND irl.UnitCost > 0
            ORDER BY irl.CreatedAt ASC
            LIMIT 1
        )
    ) AS totalCost,

    -- Profit = sales - cost
    SUM(eol.ExportedQuantity * eol.UnitPrice) - SUM(
        eol.ExportedQuantity * (
            SELECT IFNULL(irl.UnitCost, 0)
            FROM ImportReceiptLineItem irl
            WHERE irl.LotNumber = eol.LotNumber
              AND irl.UnitCost IS NOT NULL
              AND irl.UnitCost > 0
            ORDER BY irl.CreatedAt ASC
            LIMIT 1
        )
    ) AS profit,

    eh.OrderDate,
    eh.WarehouseID

FROM ExportOrderLineItem eol
JOIN ExportOrderHeader eh ON eol.OrderID = eh.OrderID
JOIN Product p ON p.ProductID = eol.ProductID

WHERE eh.WarehouseID = 1
--   AND eh.OrderDate BETWEEN ? AND ?
  AND eh.ExportPurpose = 'sale'

GROUP BY eol.LotNumber, eol.ProductID, eol.UnitPrice, eh.OrderDate, eh.WarehouseID, p.Name
ORDER BY eh.OrderDate ASC, eol.ProductID;

