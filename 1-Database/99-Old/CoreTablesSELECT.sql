-- SHOW TABLEs;

-- SELECT * FROM Manufacturer;

-- SELECT * FROM ProductCategory;

-- SELECT * FROM Product;

-- SELECT * FROM ProductDimensions;

-- SELECT * FROM ProductInstance;

-- SELECT * FROM Warehouse;

-- SELECT p.ProductID, p.Name, p.PhotoURL, p.Origin, p.WholesalePrice, p.RetailPrice, 
--        p.Status, m.Name AS Manufacturer, c.Name AS Category
-- FROM Product p
-- JOIN Manufacturer m ON p.ManufacturerID = m.ManufacturerID
-- JOIN ProductCategory c ON p.CategoryID = c.CategoryID;