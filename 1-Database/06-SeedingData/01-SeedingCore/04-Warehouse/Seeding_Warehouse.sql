-- Clean existing data and reset ID
DELETE FROM Warehouse;
ALTER TABLE Warehouse AUTO_INCREMENT = 1;

-- Insert sample Vietnamese warehouses
INSERT INTO Warehouse (Name, StreetAddress, StateProvince, City, PostalCode, Country, Phone, Email, Status)
VALUES 
('Kho Hà Nội', '123 Đường Giải Phóng', 'Hà Nội', 'Hà Nội', '100000', 'Việt Nam', '0241234567', 'hanoi.kho@example.com', 'active'),
('Kho Hồ Chí Minh', '456 Đường Cộng Hòa', 'TP. Hồ Chí Minh', 'Hồ Chí Minh', '700000', 'Việt Nam', '0287654321', 'hcm.kho@example.com', 'active'),
('Kho Đà Nẵng', '789 Đường Hải Phòng', 'Đà Nẵng', 'Đà Nẵng', '550000', 'Việt Nam', '0236378912', 'danang.kho@example.com', 'active'),
('Kho Cần Thơ', '321 Đường Nguyễn Văn Linh', 'Cần Thơ', 'Cần Thơ', '900000', 'Việt Nam', '0292384765', 'cantho.kho@example.com', 'active'),
('Kho Hải Phòng', '654 Đường Trần Nguyên Hãn', 'Hải Phòng', 'Hải Phòng', '180000', 'Việt Nam', '0225398462', 'haiphong.kho@example.com', 'active');