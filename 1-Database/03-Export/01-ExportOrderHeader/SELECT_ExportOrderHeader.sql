 SELECT
    o.OrderID,
    o.OrderNumber,

    o.CustomerID,
    customer.ContactName AS CustomerName,
    o.CarrierID,
    carrier.ContactName AS CarrierName,

    o.WarehouseID,
    w.Name AS WarehouseName,

    o.ShippingAddressID,
    a.StreetAddress,
    a.City,
    a.StateProvince,
    a.PostalCode,
    a.Country,

    o.OrderDate,
    o.RequestedDeliveryDate,
    o.ShippingMethod,
    o.OrderStatus,
    o.Notes,
    o.Status,
    o.ReceiptID,

    o.CreatedBy,
    createdByUser.FullName AS CreatedByName,
    o.UpdatedBy,
    updatedByUser.FullName AS UpdatedByName,
    o.CreatedAt,
    o.UpdatedAt
FROM ExportOrderHeader o
LEFT JOIN Party customer ON o.CustomerID = customer.PartyID
LEFT JOIN Party carrier ON o.CarrierID = carrier.PartyID
LEFT JOIN Warehouse w ON o.WarehouseID = w.WarehouseID
LEFT JOIN Address a ON o.ShippingAddressID = a.AddressID
LEFT JOIN User createdByUser ON o.CreatedBy = createdByUser.UserID
LEFT JOIN User updatedByUser ON o.UpdatedBy = updatedByUser.UserID
ORDER BY o.OrderID