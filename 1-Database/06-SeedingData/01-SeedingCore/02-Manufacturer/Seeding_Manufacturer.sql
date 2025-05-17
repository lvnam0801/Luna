-- Clear and reset ID
DELETE FROM Manufacturer;
ALTER TABLE Manufacturer AUTO_INCREMENT = 1;

-- Insert sample Vietnamese manufacturers
INSERT INTO Manufacturer (
    Name, LogoURL, StreetAddress, StateProvince, City,
    PostalCode, Country, Phone, Email, Website, Status
) VALUES
('Công ty TNHH Điện máy Sài Gòn', 'https://example.com/logo1.png', '123 Đường 3/2', 'Quận 10', 'TP. Hồ Chí Minh', '700000', 'Việt Nam', '0909123456', 'contact@dienmaysaigon.vn', 'https://dienmaysaigon.vn', 'active'),
('Tập đoàn Gia Dụng Hà Nội', 'https://example.com/logo2.png', '45 Nguyễn Trãi', 'Thanh Xuân', 'Hà Nội', '100000', 'Việt Nam', '02412345678', 'support@gdhn.vn', 'https://giadunghanoi.vn', 'active'),
('Công ty Thời Trang Việt', 'https://example.com/logo3.png', '789 Lê Lợi', 'Hải Châu', 'Đà Nẵng', '550000', 'Việt Nam', '0909888777', 'info@thoitrangviet.vn', 'https://thoitrangviet.vn', 'active'),
('Nhà máy Thiết bị Minh Tâm', 'https://example.com/logo4.png', 'Khu CN Tân Tạo', 'Bình Tân', 'TP. Hồ Chí Minh', '740000', 'Việt Nam', '02839456789', 'sales@minhtamtech.vn', 'https://minhtamtech.vn', 'active'),
('Công ty CP Thể Thao Đông Á', 'https://example.com/logo5.png', '12 Phạm Văn Đồng', 'Cầu Giấy', 'Hà Nội', '100000', 'Việt Nam', '0911223344', 'hello@thethaodonga.vn', 'https://thethaodonga.vn', 'active');