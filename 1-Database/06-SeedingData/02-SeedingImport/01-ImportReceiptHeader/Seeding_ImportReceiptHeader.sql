-- USE your_database_name;

-- Remove old data
DELETE FROM ImportReceiptHeader;

-- Reset ID counter
ALTER TABLE ImportReceiptHeader AUTO_INCREMENT = 1;

-- Insert new records with realistic Vietnamese-style values
INSERT INTO ImportReceiptHeader (
    SupplierID, CarrierID, WarehouseID, ReceivingDockID, OriginLocationID,
    ReceiptNumber, ASNNumber, PONumber,
    ExpectedArrivalDate, ActualArrivalDate, ReceiptStatus, Notes,
    CreatedBy, UpdatedBy, Status
)
VALUES
(1, 1, 1, 1, 1, 'PN001-24', 'ASN-001-VN', 'PO-001-VN', DATE_SUB(CURDATE(), INTERVAL 1 DAY), DATE_SUB(CURDATE(), INTERVAL 1 DAY), 'pending', 'Chờ xử lý tại kho A', 1, 1, 'active'),
(2, 2, 1, 1, 2, 'PN002-24', 'ASN-002-VN', 'PO-002-VN', DATE_SUB(CURDATE(), INTERVAL 2 DAY), DATE_SUB(CURDATE(), INTERVAL 2 DAY), 'in_progress', 'Đang kiểm tra số lượng', 2, 2, 'active'),
(3, 3, 1, 2, 3, 'PN003-24', 'ASN-003-VN', 'PO-003-VN', DATE_SUB(CURDATE(), INTERVAL 3 DAY), DATE_SUB(CURDATE(), INTERVAL 3 DAY), 'completed', NULL, 3, 3, 'active'),
(4, 4, 1, 3, 4, 'PN004-24', 'ASN-004-VN', 'PO-004-VN', DATE_SUB(CURDATE(), INTERVAL 4 DAY), DATE_SUB(CURDATE(), INTERVAL 4 DAY), 'cancelled', 'Hủy do nhà cung cấp gửi sai hàng', 4, 4, 'active'),
(5, 5, 1, 4, 5, 'PN005-24', 'ASN-005-VN', 'PO-005-VN', DATE_SUB(CURDATE(), INTERVAL 5 DAY), DATE_SUB(CURDATE(), INTERVAL 5 DAY), 'pending', NULL, 5, 5, 'active'),
(1, 1, 1, 1, 2, 'PN006-24', 'ASN-006-VN', 'PO-006-VN', DATE_SUB(CURDATE(), INTERVAL 6 DAY), DATE_SUB(CURDATE(), INTERVAL 6 DAY), 'in_progress', 'Kiểm tra chất lượng sản phẩm', 1, 1, 'active'),
(2, 2, 1, 2, 3, 'PN007-24', 'ASN-007-VN', 'PO-007-VN', DATE_SUB(CURDATE(), INTERVAL 7 DAY), DATE_SUB(CURDATE(), INTERVAL 7 DAY), 'completed', NULL, 2, 2, 'active'),
(3, 3, 1, 3, 4, 'PN008-24', 'ASN-008-VN', 'PO-008-VN', DATE_SUB(CURDATE(), INTERVAL 8 DAY), DATE_SUB(CURDATE(), INTERVAL 8 DAY), 'cancelled', 'Đơn hàng bị hủy do lỗi vận chuyển', 3, 3, 'active'),
(4, 4, 1, 4, 5, 'PN009-24', 'ASN-009-VN', 'PO-009-VN', DATE_SUB(CURDATE(), INTERVAL 9 DAY), DATE_SUB(CURDATE(), INTERVAL 9 DAY), 'pending', NULL, 4, 4, 'active'),
(5, 5, 1, 5, 1, 'PN010-24', 'ASN-010-VN', 'PO-010-VN', DATE_SUB(CURDATE(), INTERVAL 10 DAY), DATE_SUB(CURDATE(), INTERVAL 10 DAY), 'in_progress', 'Kho B đang tiếp nhận hàng', 5, 5, 'active'),
(1, 2, 1, 1, 2, 'PN011-24', 'ASN-011-VN', 'PO-011-VN', DATE_SUB(CURDATE(), INTERVAL 11 DAY), DATE_SUB(CURDATE(), INTERVAL 11 DAY), 'completed', NULL, 1, 1, 'active'),
(2, 3, 1, 2, 3, 'PN012-24', 'ASN-012-VN', 'PO-012-VN', DATE_SUB(CURDATE(), INTERVAL 12 DAY), DATE_SUB(CURDATE(), INTERVAL 12 DAY), 'cancelled', 'Không đạt chất lượng kiểm tra', 2, 2, 'active'),
(3, 4, 1, 3, 4, 'PN013-24', 'ASN-013-VN', 'PO-013-VN', DATE_SUB(CURDATE(), INTERVAL 13 DAY), DATE_SUB(CURDATE(), INTERVAL 13 DAY), 'pending', NULL, 3, 3, 'active'),
(4, 5, 1, 4, 5, 'PN014-24', 'ASN-014-VN', 'PO-014-VN', DATE_SUB(CURDATE(), INTERVAL 14 DAY), DATE_SUB(CURDATE(), INTERVAL 14 DAY), 'in_progress', NULL, 4, 4, 'active'),
(5, 1, 1, 5, 1, 'PN015-24', 'ASN-015-VN', 'PO-015-VN', DATE_SUB(CURDATE(), INTERVAL 1 DAY), DATE_SUB(CURDATE(), INTERVAL 1 DAY), 'completed', 'Đã nhập kho thành công', 5, 5, 'active'),
(1, 3, 1, 1, 2, 'PN016-24', 'ASN-016-VN', 'PO-016-VN', DATE_SUB(CURDATE(), INTERVAL 2 DAY), DATE_SUB(CURDATE(), INTERVAL 2 DAY), 'cancelled', NULL, 1, 1, 'active'),
(2, 4, 1, 2, 3, 'PN017-24', 'ASN-017-VN', 'PO-017-VN', DATE_SUB(CURDATE(), INTERVAL 3 DAY), DATE_SUB(CURDATE(), INTERVAL 3 DAY), 'pending', 'Chưa có nhân viên xác nhận', 2, 2, 'active'),
(3, 5, 1, 3, 4, 'PN018-24', 'ASN-018-VN', 'PO-018-VN', DATE_SUB(CURDATE(), INTERVAL 4 DAY), DATE_SUB(CURDATE(), INTERVAL 4 DAY), 'in_progress', NULL, 3, 3, 'active'),
(4, 1, 1, 4, 5, 'PN019-24', 'ASN-019-VN', 'PO-019-VN', DATE_SUB(CURDATE(), INTERVAL 5 DAY), DATE_SUB(CURDATE(), INTERVAL 5 DAY), 'completed', 'Phiếu đã được đóng', 4, 4, 'active'),
(5, 2, 1, 5, 1, 'PN020-24', 'ASN-020-VN', 'PO-020-VN', DATE_SUB(CURDATE(), INTERVAL 6 DAY), DATE_SUB(CURDATE(), INTERVAL 6 DAY), 'cancelled', 'Khách yêu cầu hủy đơn', 5, 5, 'active');