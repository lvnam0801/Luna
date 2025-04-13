-- INSERT INTO ProductCategory (Name, Description, ParentID, Status)
-- VALUES 
--     ('Giày Dép', 'Danh mục chứa các loại giày và dép', NULL, 'Active'),
--     ('Quần Áo', 'Danh mục chứa các loại quần áo thời trang', NULL, 'Active'),
--     ('Phụ Kiện', 'Danh mục chứa các loại phụ kiện đi kèm', NULL, 'Active');

-- INSERT INTO ProductCategory (Name, Description, ParentID, Status)
-- VALUES 
--     ('Điện Thoại', 'Danh mục chứa các loại điện thoại di động', NULL, 'Active'),
--     ('Máy Tính Bảng', 'Danh mục chứa các loại máy tính bảng', NULL, 'Active'),
--     ('Tai Nghe', 'Danh mục chứa các loại tai nghe có dây và không dây', NULL, 'Active');

-- INSERT INTO Manufacturer (Name, LogoURL, StreetAddress, City, StateProvince, PostalCode, Country, Phone, Email, Website, Status)
-- VALUES 
-- ('Apple Inc.', 'https://apple.com/logo.png', 'One Apple Park Way', 'Cupertino', 'California', '95014', 'USA', '+1-408-996-1010', 'contact@apple.com', 'https://apple.com', 'Active'),
-- ('Samsung Electronics', 'https://samsung.com/logo.png', '129 Samsung-ro', 'Suwon', 'Gyeonggi-do', '16677', 'South Korea', '+82-2-2255-0114', 'support@samsung.com', 'https://samsung.com', 'Active');

-- DELETE FROM Product;

-- INSERT INTO Product (Name, PhotoURL, Origin, WholesalePrice, RetailPrice, Status, ManufacturerID, CategoryID)
-- VALUES 
--     -- Apple iPhones
--     ('iPhone 15', 'https://example.com/iphone15.png', 'USA', 15000000, 17000000, 'active', 1, 4),
--     ('iPhone 15 Pro', 'https://example.com/iphone15pro.png', 'USA', 15000000, 17000000, 'active', 1, 4),

--     -- Samsung Phones
--     ('Samsung Galaxy S23', 'https://example.com/galaxy-s23.png', 'South Korea', 15000000, 94900000, 'active', 2, 4),
--     ('Samsung Galaxy Z Flip5', 'https://example.com/galaxy-zflip5.png', 'South Korea', 90000000, 109900000, 'active', 2, 4),

--     -- Apple Tablets
--     ('iPad Air 2023', 'https://example.com/ipad-air.png', 'China', 60000000, 79900000, 'active', 1, 5),
--     ('iPad Pro M2', 'https://example.com/ipad-pro.png', 'China', 100000000, 17000000, 'active', 1, 5),

--     -- Samsung Tablets
--     ('Samsung Galaxy Tab S9', 'https://example.com/galaxy-tab-s9.png', 'South Korea', 70000000, 89900000, 'active', 2, 5),

--     -- Apple & Samsung Headphones
--     ('AirPods Pro 2', 'https://example.com/airpods-pro2.png', 'Vietnam', 20000000, 24900000, 'active', 1, 6),
--     ('Samsung Galaxy Buds2', 'https://example.com/galaxy-buds2.png', 'Vietnam', 12000000, 14900000, 'active', 2, 6);

-- INSERT INTO ProductDimensions (ProductID, DimensionType, Value, Unit) 
-- VALUES 
-- (28, 'Height', 10.5, 'cm'),
-- (28, 'Width', 20.0, 'cm'),
-- (28, 'Weight', 1.2, 'kg'),
-- (29, 'Length', 30.0, 'cm');

-- SELECT * FROM ProductDimensions;

-- INSERT INTO Warehouse (Name, StreetAddress, City, StateProvince, PostalCode, Country, Phone, Email, Status)

-- VALUES
    -- ('HCM Warehouse', '123 Nguyen Van Linh', 'Ho Chi Minh', 'Ho Chi Minh', '700000', 'Vietnam', '+84-28-1234-5678', 'hcm.nguyen@gmail.com', 'active');
    -- ,
    -- ('HN Warehouse', '456 Le Duan', 'Ha Noi', 'Ha Noi', '100000', 'Vietnam', '+84-24-1234-5678', 'HN@gmail.com', 'active'),
    -- ('Testing Name', '123 Nguyen', 'City Testing', 'State testing', '700001', 'Vietnam', '+84-28-1234-5679', 'Testing@gmail.com', 'active');


-- INSERT INTO ProductInstance (ProductID, WarehouseID, SKU, Status)
-- VALUES 
-- (44, 1, 'AP-001', 'In=')