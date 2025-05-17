-- Delete and reset identity
DELETE FROM ProductCategory;
ALTER TABLE ProductCategory AUTO_INCREMENT = 1;

-- Main categories
INSERT INTO ProductCategory (Name, Description, ParentID, Status) VALUES
('Thiết bị điện tử', 'Các loại thiết bị điện tử như TV, máy tính,...', NULL, 'active'),
('Gia dụng', 'Đồ gia dụng cho gia đình', NULL, 'active'),
('Thời trang', 'Quần áo và phụ kiện thời trang', NULL, 'active'),
('Thể thao & Ngoài trời', 'Dụng cụ thể thao và hoạt động ngoài trời', NULL, 'active');

-- Subcategories
INSERT INTO ProductCategory (Name, Description, ParentID, Status) VALUES
('TV & Màn hình', 'Các loại TV và màn hình LCD, LED...', 1, 'active'),
('Máy tính & Phụ kiện', 'Laptop, bàn phím, chuột, phụ kiện khác...', 1, 'active'),
('Đồ bếp', 'Nồi, chảo, bếp điện, đồ dùng nhà bếp...', 2, 'active'),
('Làm sạch nhà cửa', 'Máy hút bụi, cây lau nhà...', 2, 'active'),
('Quần áo nam', 'Áo sơ mi, quần jeans, áo khoác nam...', 3, 'active'),
('Quần áo nữ', 'Đầm, váy, áo sơ mi nữ...', 3, 'active'),
('Dụng cụ thể thao', 'Bóng, vợt, tạ, dây nhảy...', 4, 'active'),
('Đồ cắm trại', 'Lều trại, túi ngủ, đèn pin...', 4, 'active');