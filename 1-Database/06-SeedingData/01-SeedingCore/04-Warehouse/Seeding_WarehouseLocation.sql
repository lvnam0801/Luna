-- Clear old data and reset auto-increment
DELETE FROM Location;
ALTER TABLE Location AUTO_INCREMENT = 1;

-- Insert seeding data for warehouse locations
INSERT INTO Location (WarehouseID, LocationName, LocationType, StorageType, Unit, Capacity, Status)
VALUES
-- Kho Chính (ID = 1)
(1, 'Khu tiếp nhận 1', 'receiving', 'floor', 'thùng', 500, 'active'),
(1, 'Khu lưu trữ khô', 'storage', 'shelf', 'thùng', 2000, 'active'),
(1, 'Khu đóng gói', 'packing', 'zone', 'thùng', 300, 'active'),
(1, 'Khu xuất hàng', 'shipping', 'floor', 'thùng', 300, 'active'),
(1, 'Khu cách ly', 'quarantine', 'bin', 'thùng', 100, 'active'),

-- Kho Miền Nam (ID = 2)
(2, 'Tiếp nhận hàng', 'receiving', 'floor', 'bao', 400, 'active'),
(2, 'Lưu trữ chính', 'storage', 'shelf', 'bao', 1500, 'active'),
(2, 'Đóng gói số 2', 'packing', 'zone', 'bao', 250, 'active'),

-- Kho Miền Bắc (ID = 3)
(3, 'Tiếp nhận kho Bắc', 'receiving', 'floor', 'kiện', 600, 'active'),
(3, 'Lưu trữ lạnh', 'storage', 'cold_room', 'kiện', 1200, 'active'),
(3, 'Xuất hàng Bắc', 'shipping', 'pallet', 'kiện', 350, 'active'),

-- Kho Kỹ Thuật (ID = 4)
(4, 'Vùng sửa chữa', 'quarantine', 'zone', 'thùng', 150, 'active'),
(4, 'Khu lưu trữ linh kiện', 'storage', 'bin', 'thùng', 800, 'active'),

-- Kho Trưng Bày (ID = 5)
(5, 'Khu triển lãm', 'storage', 'floor', 'sản phẩm', 500, 'active'),
(5, 'Lối vào tiếp nhận', 'receiving', 'floor', 'sản phẩm', 200, 'active');