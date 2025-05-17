DELETE FROM ExportOrderHeader;
ALTER TABLE ExportOrderHeader AUTO_INCREMENT = 1;


INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    2, 2, 1, 3,
    'EX-20240501', '2025-05-10', '2025-05-12', 'Giao hàng tiêu chuẩn',
    'shipped', 'Khách yêu cầu giao trước 17h', 'active', 3, '2025-05-10 10:56:55',
    3, '2025-05-11 00:56:55', 8
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    5, 3, 1, 8,
    'EX-20240502', '2025-04-30', '2025-05-01', 'Giao hàng tiêu chuẩn',
    'pending', NULL, 'active', 3, '2025-04-30 14:56:55',
    5, '2025-05-02 11:56:55', NULL
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    5, 4, 1, 7,
    'EX-20240503', '2025-04-29', '2025-05-04', 'Giao hàng nhanh',
    'cancelled', NULL, 'active', 2, '2025-04-29 07:56:55',
    4, '2025-04-30 03:56:55', 17
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    5, 5, 1, 6,
    'EX-20240504', '2025-05-02', '2025-05-04', 'Giao hỏa tốc',
    'pending', 'Khách yêu cầu giao trước 17h', 'active', 1, '2025-05-01 20:56:55',
    2, '2025-05-03 10:56:55', NULL
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    5, 4, 1, 5,
    'EX-20240505', '2025-05-05', '2025-05-06', 'Giao hàng tiêu chuẩn',
    'shipped', NULL, 'active', 4, '2025-05-04 08:56:55',
    5, '2025-05-04 10:56:55', NULL
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    1, 4, 1, 1,
    'EX-20240506', '2025-05-04', '2025-05-05', 'Giao hỏa tốc',
    'processing', 'Khách yêu cầu giao trước 17h', 'active', 4, '2025-05-03 23:56:55',
    3, '2025-05-05 02:56:55', 6
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    1, 1, 1, 6,
    'EX-20240507', '2025-05-02', '2025-05-03', 'Giao hàng tiêu chuẩn',
    'cancelled', 'Khách yêu cầu giao trước 17h', 'active', 5, '2025-05-02 04:56:55',
    1, '2025-05-03 23:56:55', 19
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    5, 4, 1, 8,
    'EX-20240508', '2025-05-10', '2025-05-13', 'Giao hàng nhanh',
    'processing', NULL, 'active', 1, '2025-05-09 08:56:55',
    1, '2025-05-09 15:56:55', 5
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    4, 2, 1, 10,
    'EX-20240509', '2025-05-01', '2025-05-03', 'Giao hàng tiêu chuẩn',
    'pending', 'Khách yêu cầu giao trước 17h', 'active', 3, '2025-04-30 00:56:55',
    1, '2025-04-30 12:56:55', 7
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    5, 4, 1, 2,
    'EX-20240510', '2025-05-02', '2025-05-04', 'Giao hàng tiêu chuẩn',
    'shipped', NULL, 'active', 3, '2025-04-30 20:56:55',
    1, '2025-05-01 21:56:55', 10
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    5, 1, 1, 6,
    'EX-20240511', '2025-04-30', '2025-05-03', 'Giao hàng nhanh',
    'pending', 'Khách yêu cầu giao trước 17h', 'active', 4, '2025-04-28 23:56:55',
    3, '2025-04-29 10:56:55', 4
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    1, 5, 1, 3,
    'EX-20240512', '2025-05-08', '2025-05-10', 'Giao hàng tiêu chuẩn',
    'shipped', NULL, 'active', 4, '2025-05-07 22:56:55',
    1, '2025-05-08 10:56:55', 18
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    4, 1, 1, 8,
    'EX-20240513', '2025-05-06', '2025-05-07', 'Giao hàng tiêu chuẩn',
    'processing', NULL, 'active', 3, '2025-05-06 13:56:55',
    5, '2025-05-08 02:56:55', 7
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    4, 2, 1, 8,
    'EX-20240514', '2025-05-02', '2025-05-03', 'Giao hàng tiêu chuẩn',
    'processing', 'Khách yêu cầu giao trước 17h', 'active', 5, '2025-05-02 09:56:55',
    2, '2025-05-02 14:56:55', 19
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    3, 1, 1, 4,
    'EX-20240515', '2025-05-08', '2025-05-13', 'Giao hàng nhanh',
    'cancelled', NULL, 'active', 1, '2025-05-08 03:56:55',
    2, '2025-05-08 06:56:55', 6
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    3, 4, 1, 10,
    'EX-20240516', '2025-04-30', '2025-05-01', 'Giao hỏa tốc',
    'pending', 'Khách yêu cầu giao trước 17h', 'active', 2, '2025-04-29 03:56:55',
    4, '2025-04-29 17:56:55', NULL
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    3, 4, 1, 8,
    'EX-20240517', '2025-05-06', '2025-05-07', 'Giao hàng tiêu chuẩn',
    'cancelled', NULL, 'active', 2, '2025-05-05 10:56:55',
    3, '2025-05-07 09:56:55', 8
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    4, 3, 1, 3,
    'EX-20240518', '2025-05-10', '2025-05-11', 'Giao hàng tiêu chuẩn',
    'processing', NULL, 'active', 1, '2025-05-08 23:56:55',
    4, '2025-05-10 08:56:55', 3
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    4, 4, 1, 10,
    'EX-20240519', '2025-05-10', '2025-05-13', 'Giao hỏa tốc',
    'processing', 'Khách yêu cầu giao trước 17h', 'active', 3, '2025-05-09 18:56:55',
    2, '2025-05-10 23:56:55', 14
);
INSERT INTO ExportOrderHeader (
    CustomerID, CarrierID, WarehouseID, ShippingAddressID,
    OrderNumber, OrderDate, RequestedDeliveryDate, ShippingMethod,
    OrderStatus, Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, ReceiptID
) VALUES (
    1, 3, 1, 3,
    'EX-20240520', '2025-05-03', '2025-05-08', 'Giao hỏa tốc',
    'cancelled', 'Khách yêu cầu giao trước 17h', 'active', 3, '2025-05-02 05:56:55',
    5, '2025-05-04 01:56:55', 6
);