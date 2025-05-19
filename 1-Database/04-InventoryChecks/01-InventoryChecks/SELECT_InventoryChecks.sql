SELECT
    d.InventoryCheckDetailsID,
    d.InventoryCheckID,
    d.SKUItemID,
    s.SKU,
    s.LocationID,
    l.LocationName AS LocationName,
    d.SystemQuantity,
    d.ActualQuantity,
    d.QuantityDifferenceReason,
    d.Note,
    d.Status
FROM InventoryCheckDetails d
JOIN SKUItem s ON d.SKUItemID = s.ItemID
JOIN Location l ON s.LocationID = l.LocationID
WHERE d.InventoryCheckID = 4