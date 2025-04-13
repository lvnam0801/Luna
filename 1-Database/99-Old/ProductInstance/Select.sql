-- SELECT pi.InstanceID, pi.SKU, pi.Quantity, pi.ExpiredDate, p.Name, p.PhotoURL, p.Origin, p.WholesalePrice, p.RetailPrice, w.Name, pi.Status
-- FROM ProductInstance pi
-- JOIN Product p ON p.ProductID = pi.ProductID
-- JOIN Warehouse w ON w.warehouseID = pi.WarehouseID

-- SELECT * FROM ProductInstance;

SELECT pi.InstanceID, pi.SKU, pi.Quantity, pi.ExpiredDate, p.Name, p.PhotoURL, p.Origin, p.WholesalePrice, p.RetailPrice, w.Name, m.Name, c.Name, d.DimensionType, d.Value, d.Unit, pi.Status
FROM ProductInstance pi
JOIN Product p ON p.ProductID = pi.ProductID
JOIN Warehouse w ON w.warehouseID = pi.WarehouseID
JOIN Manufacturer m ON m.ManufacturerID = p.ManufacturerID
JOIN ProductCategory c ON c.CategoryID = p.CategoryID
JOIN ProductDimensions d ON d.ProductID = p.ProductID