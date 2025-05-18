SELECT
s.ShipmentID, s.ShipmentNumber,
s.OrderID, o.OrderNumber,
s.CarrierID, c.ContactName AS CarrierName,
w.Name AS WarehouseName,
s.ShipFromLocationID, l.LocationName AS ShipFromLocationName,
s.ShipToAddressID,
a.StreetAddress, a.City, a.StateProvince, a.PostalCode, a.Country,
s.ShippedDate, s.ExpectedArrivalDate, s.ActualArrivalDate,
s.ShipmentStatus, s.Notes, s.Status,
s.CreatedBy, cu.FullName AS CreatedByName,
s.CreatedAt, s.UpdatedBy, uu.FullName AS UpdatedByName, s.UpdatedAt
FROM Shipment s
JOIN ExportOrderHeader o ON s.OrderID = o.OrderID
LEFT JOIN Party c ON s.CarrierID = c.PartyID
LEFT JOIN Warehouse w ON s.WarehouseID = w.WarehouseID
LEFT JOIN Location l ON s.ShipFromLocationID = l.LocationID
LEFT JOIN Address a ON s.ShipToAddressID = a.AddressID
LEFT JOIN User cu ON s.CreatedBy = cu.UserID
LEFT JOIN User uu ON s.UpdatedBy = uu.UserID
WHERE s.OrderID = 1
ORDER BY s.ShipmentID