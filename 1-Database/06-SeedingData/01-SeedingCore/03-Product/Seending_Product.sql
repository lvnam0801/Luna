-- Clear existing data and reset identity
DELETE FROM Product;
ALTER TABLE Product AUTO_INCREMENT = 1;

-- Insert product seed data
INSERT INTO Product (
    ProductCode, Name, PhotoURL, Origin,
    RetailPrice, WholesalePrice,
    ManufacturerID, CategoryID,
    Status
) VALUES
('SP001', 'Điện thoại Vsmart Active 3', 'https://example.com/sp001.jpg', 'Việt Nam', 3500000, 2900000, 1, 2, 'active'),
('SP002', 'Nồi cơm điện Sunhouse 1.8L', 'https://example.com/sp002.jpg', 'Việt Nam', 720000, 600000, 2, 4, 'active'),
('SP003', 'Áo khoác Unisex Mùa Đông', 'https://example.com/sp003.jpg', 'Việt Nam', 420000, 330000, 3, 5, 'active'),
('SP004', 'Máy lọc không khí Daikin', 'https://example.com/sp004.jpg', 'Nhật Bản', 6500000, 5800000, 4, 3, 'active'),
('SP005', 'Giày thể thao Sneaker Đông Á', 'https://example.com/sp005.jpg', 'Việt Nam', 850000, 690000, 5, 6, 'active'),
('SP006', 'Laptop Acer Aspire 7', 'https://example.com/sp006.jpg', 'Trung Quốc', 16500000, 14800000, 1, 2, 'active'),
('SP007', 'Tủ lạnh Panasonic Inverter', 'https://example.com/sp007.jpg', 'Thái Lan', 9800000, 8600000, 2, 4, 'active'),
('SP008', 'Bàn chải điện Xiaomi', 'https://example.com/sp008.jpg', 'Trung Quốc', 450000, 370000, 3, 4, 'active'),
('SP009', 'Máy ép chậm Bluestone', 'https://example.com/sp009.jpg', 'Việt Nam', 2600000, 2200000, 4, 4, 'active'),
('SP010', 'Áo sơ mi nam Việt Tiến', 'https://example.com/sp010.jpg', 'Việt Nam', 320000, 250000, 5, 5, 'active'),
('SP011', 'Đèn học LED Rạng Đông', 'https://example.com/sp011.jpg', 'Việt Nam', 150000, 120000, 1, 4, 'active'),
('SP012', 'Balo học sinh Hami', 'https://example.com/sp012.jpg', 'Việt Nam', 280000, 210000, 2, 6, 'active'),
('SP013', 'Máy in Canon LBP2900', 'https://example.com/sp013.jpg', 'Nhật Bản', 2850000, 2500000, 3, 2, 'active'),
('SP014', 'Ổ cứng di động WD 1TB', 'https://example.com/sp014.jpg', 'Mỹ', 1450000, 1280000, 4, 2, 'active'),
('SP015', 'Nồi chiên không dầu Lock&Lock', 'https://example.com/sp015.jpg', 'Hàn Quốc', 1890000, 1650000, 5, 4, 'active');