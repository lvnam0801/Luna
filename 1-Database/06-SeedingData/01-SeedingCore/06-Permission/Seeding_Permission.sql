-- Clear old data and reset auto-increment
DELETE FROM Permission;
ALTER TABLE Permission AUTO_INCREMENT = 1;

-- Insert permission records
INSERT INTO Permission (PermissionName, Description, Status) VALUES
-- Product
('xem_san_pham', 'Cho phép người dùng xem danh sách sản phẩm', 'active'),
('them_san_pham', 'Cho phép người dùng thêm sản phẩm mới vào hệ thống', 'active'),
('sua_san_pham', 'Cho phép người dùng chỉnh sửa thông tin sản phẩm', 'active'),
('xoa_san_pham', 'Cho phép người dùng xóa sản phẩm khỏi hệ thống', 'active'),

-- Warehouse
('xem_kho', 'Cho phép người dùng xem danh sách kho', 'active'),
('quan_ly_kho', 'Cho phép người dùng cập nhật và quản lý kho hàng', 'active'),

-- Import
('xem_nhap_kho', 'Cho phép người dùng xem phiếu nhập kho', 'active'),
('tao_phieu_nhap', 'Cho phép người dùng tạo phiếu nhập hàng mới', 'active'),

-- Export
('xem_xuat_kho', 'Cho phép người dùng xem phiếu xuất kho', 'active'),
('tao_phieu_xuat', 'Cho phép người dùng tạo phiếu xuất hàng', 'active'),

-- User
('xem_nguoi_dung', 'Cho phép xem thông tin người dùng trong hệ thống', 'active'),
('quan_ly_nguoi_dung', 'Cho phép thêm, sửa, xóa người dùng', 'active'),

-- Settings
('phan_quyen', 'Quản lý phân quyền người dùng', 'active'),
('xem_hoat_dong', 'Xem nhật ký hoạt động của hệ thống', 'active');