SELECT s.*, 
        o.OrderNumber, 
        c.ContactName AS CarrierName,
        l.LocationName AS ShipFromLocationName,
        a.StreetAddress, a.City, a.StateProvince, a.PostalCode, a.Country,
        cu1.FullName AS CreatedByName,
        cu2.FullName AS UpdatedByName
FROM Shipment s
JOIN ExportOrderHeader o ON s.OrderID = o.OrderID
LEFT JOIN Party c ON s.CarrierID = c.PartyID
LEFT JOIN Location l ON s.ShipFromLocationID = l.LocationID
LEFT JOIN Address a ON s.ShipToAddressID = a.AddressID
LEFT JOIN User cu1 ON s.CreatedBy = cu1.UserID
LEFT JOIN User cu2 ON s.UpdatedBy = cu2.UserID
WHERE s.OrderID = 1