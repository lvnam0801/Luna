-- Clear old data and reset auto-increment
DELETE FROM Party;
ALTER TABLE Party AUTO_INCREMENT = 1;

-- Insert sample parties (5 for each PartyType)
INSERT INTO Party (PartyType, ContactName, StreetAddress, StateProvince, City, PostalCode, Country, Phone, Email, Note, Status)
VALUES
-- Suppliers
('supplier', 'Công ty Nguyên liệu Việt Phát', '12 Nguyễn Văn Linh', 'Hồ Chí Minh', 'Hồ Chí Minh', '700000', 'Việt Nam', '0912000001', 'vietphat@sup.vn', 'Chuyên cung cấp nguyên liệu nông sản', 'active'),
('supplier', 'Công ty TNHH Thiết bị Ánh Dương', '56 Phạm Văn Đồng', 'Hà Nội', 'Hà Nội', '100000', 'Việt Nam', '0912000002', 'anhduong@sup.vn', 'Thiết bị điện dân dụng', 'active'),
('supplier', 'Công ty Sắt Thép Hoàng Long', '102 Trường Chinh', 'Đà Nẵng', 'Đà Nẵng', '550000', 'Việt Nam', '0912000003', 'hoanglong@sup.vn', 'Sắt thép công trình', 'active'),
('supplier', 'Công ty Bao bì Việt Nam', '1A Lê Duẩn', 'Hồ Chí Minh', 'Hồ Chí Minh', '700000', 'Việt Nam', '0912000004', 'baobi@sup.vn', 'Bao bì đóng gói', 'active'),
('supplier', 'Công ty Hoá chất Đại Dương', '789 Nguyễn Trãi', 'Hà Nội', 'Hà Nội', '100000', 'Việt Nam', '0912000005', 'daiduong@sup.vn', 'Hoá chất công nghiệp', 'active'),

-- Carriers
('carrier', 'Giao hàng nhanh', '36 Nguyễn Hữu Cảnh', 'Hồ Chí Minh', 'Hồ Chí Minh', '700000', 'Việt Nam', '0922000001', 'ghn@carrier.vn', 'Đơn vị vận chuyển nội địa', 'active'),
('carrier', 'Viettel Post', '89 Trường Sơn', 'Hồ Chí Minh', 'Hồ Chí Minh', '700000', 'Việt Nam', '0922000002', 'viettelpost@carrier.vn', 'Giao hàng toàn quốc', 'active'),
('carrier', 'Giao hàng tiết kiệm', '23 Nguyễn Cư Trinh', 'Hồ Chí Minh', 'Hồ Chí Minh', '700000', 'Việt Nam', '0922000003', 'ghtk@carrier.vn', 'Phí rẻ thời gian lâu', 'active'),
('carrier', 'Best Express', '111 Pasteur', 'Hà Nội', 'Hà Nội', '100000', 'Việt Nam', '0922000004', 'best@carrier.vn', 'Express delivery', 'active'),
('carrier', 'Lalamove', '9B Cách Mạng Tháng Tám', 'Đà Nẵng', 'Đà Nẵng', '550000', 'Việt Nam', '0922000005', 'lalamove@carrier.vn', 'Ứng dụng giao hàng nhanh', 'active'),

-- Customers
('customer', 'Cửa hàng Tiện lợi A1', '45 Hoàng Hoa Thám', 'Hồ Chí Minh', 'Hồ Chí Minh', '700000', 'Việt Nam', '0933000001', 'a1store@customer.vn', 'Khách hàng bán lẻ', 'active'),
('customer', 'Cửa hàng Điện máy Bạch Mai', '67 Lý Thường Kiệt', 'Hà Nội', 'Hà Nội', '100000', 'Việt Nam', '0933000002', 'dienmaybm@customer.vn', 'Khách hàng điện máy', 'active'),
('customer', 'Siêu thị Thành Đô', '102 Quang Trung', 'Hà Nội', 'Hà Nội', '100000', 'Việt Nam', '0933000003', 'thanhdo@customer.vn', 'Khách hàng siêu thị', 'active'),
('customer', 'Công ty TNHH TMDV An Phát', '88 Nguyễn Văn Trỗi', 'Hồ Chí Minh', 'Hồ Chí Minh', '700000', 'Việt Nam', '0933000004', 'anphat@customer.vn', 'Đối tác thương mại', 'active'),
('customer', 'Showroom Nội thất Việt', '12 Trần Hưng Đạo', 'Đà Nẵng', 'Đà Nẵng', '550000', 'Việt Nam', '0933000005', 'noithatviet@customer.vn', 'Khách hàng dự án nội thất', 'active'),

-- Internal
('internal', 'Kho trung tâm Tân Bình', '1 Lạc Long Quân', 'Hồ Chí Minh', 'Hồ Chí Minh', '700000', 'Việt Nam', '0944000001', 'tbcenter@internal.vn', 'Kho nội bộ', 'active'),
('internal', 'Bộ phận Mua hàng', '7 Nam Kỳ Khởi Nghĩa', 'Hồ Chí Minh', 'Hồ Chí Minh', '700000', 'Việt Nam', '0944000002', 'purchasing@internal.vn', 'Phòng ban nội bộ', 'active'),
('internal', 'Bộ phận Kinh doanh', '17 Trường Sơn', 'Hồ Chí Minh', 'Hồ Chí Minh', '700000', 'Việt Nam', '0944000003', 'sales@internal.vn', 'Bán hàng nội bộ', 'active'),
('internal', 'Kho Chi nhánh Hà Nội', '99 Phan Chu Trinh', 'Hà Nội', 'Hà Nội', '100000', 'Việt Nam', '0944000004', 'hanoiwh@internal.vn', 'Kho miền Bắc', 'active'),
('internal', 'Kho Miền Trung', '22 Hải Phòng', 'Đà Nẵng', 'Đà Nẵng', '550000', 'Việt Nam', '0944000005', 'mtwh@internal.vn', 'Kho khu vực Trung', 'active'),

-- Other
('other', 'Công ty Đối tác ABC', '100 Điện Biên Phủ', 'Hồ Chí Minh', 'Hồ Chí Minh', '700000', 'Việt Nam', '0955000001', 'abc@other.vn', 'Đối tác phụ', 'active'),
('other', 'Đơn vị hỗ trợ kỹ thuật DEF', '76 Nguyễn Thị Minh Khai', 'Hồ Chí Minh', 'Hồ Chí Minh', '700000', 'Việt Nam', '0955000002', 'def@other.vn', 'Kỹ thuật liên kết', 'active'),
('other', 'Trung tâm bảo trì GHI', '34 Lê Hồng Phong', 'Hồ Chí Minh', 'Hồ Chí Minh', '700000', 'Việt Nam', '0955000003', 'ghi@other.vn', 'Đơn vị bảo trì', 'active'),
('other', 'Công ty Giải pháp JKL', '99 Trần Quang Diệu', 'Hà Nội', 'Hà Nội', '100000', 'Việt Nam', '0955000004', 'jkl@other.vn', 'Giải pháp hỗ trợ', 'active'),
('other', 'Cửa hàng trưng bày MNO', '28 Lê Duẩn', 'Đà Nẵng', 'Đà Nẵng', '550000', 'Việt Nam', '0955000005', 'mno@other.vn', 'Trưng bày mẫu', 'active');