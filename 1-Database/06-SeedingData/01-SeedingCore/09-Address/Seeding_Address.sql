-- Clear old data and reset identity
DELETE FROM Address;
ALTER TABLE Address AUTO_INCREMENT = 1;

-- Insert 10 example addresses
INSERT INTO Address (StreetAddress, StateProvince, City, PostalCode, Country) VALUES
('Số 123 Nguyễn Huệ', 'Quận 1', 'TP. Hồ Chí Minh', '700000', 'Việt Nam'),
('456 Lê Duẩn', 'Quận Thanh Khê', 'Đà Nẵng', '550000', 'Việt Nam'),
('789 Trần Phú', 'Quận Hà Đông', 'Hà Nội', '100000', 'Việt Nam'),
('12 Nguyễn Văn Cừ', 'Quận Long Biên', 'Hà Nội', '102000', 'Việt Nam'),
('98 Pasteur', 'Quận 3', 'TP. Hồ Chí Minh', '700300', 'Việt Nam'),
('34 Lý Thường Kiệt', 'Quận Hải Châu', 'Đà Nẵng', '551000', 'Việt Nam'),
('67 Nguyễn Sinh Sắc', 'Quận Liên Chiểu', 'Đà Nẵng', '552000', 'Việt Nam'),
('21 Hùng Vương', 'Thành phố Huế', 'Thừa Thiên Huế', '530000', 'Việt Nam'),
('888 Trường Chinh', 'Quận Tân Bình', 'TP. Hồ Chí Minh', '701000', 'Việt Nam'),
('42 Nguyễn Khuyến', 'Quận Đống Đa', 'Hà Nội', '101000', 'Việt Nam');