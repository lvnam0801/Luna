-- Clear old data and reset auto-increment
DELETE FROM Role;
ALTER TABLE Role AUTO_INCREMENT = 1;

-- Insert roles
INSERT INTO Role (RoleName, Description, Status) VALUES
('Quản trị viên', 'Toàn quyền quản lý hệ thống, bao gồm người dùng, phân quyền và dữ liệu', 'active'),
('Nhân viên kho', 'Quản lý hàng hóa, thực hiện các thao tác nhập/xuất kho', 'active'),
('Kế toán', 'Theo dõi hoạt động giao hàng và kiểm tra giá trị hàng hóa', 'active'),
('Nhân viên giao nhận', 'Xử lý vận chuyển, tạo và cập nhật lệnh giao hàng', 'active'),
('Khách', 'Chỉ có quyền truy cập hạn chế để xem dữ liệu được cấp quyền', 'active');