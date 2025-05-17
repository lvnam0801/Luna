DELETE FROM Shipment;
ALTER TABLE Shipment AUTO_INCREMENT = 1;

INSERT INTO Shipment (
    OrderID, CarrierID, ShipFromLocationID, ShipToAddressID, ShipmentNumber,
    ShippedDate, ExpectedArrivalDate, ActualArrivalDate, ShipmentStatus,
    Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    1, 3, 3, 3, 'SHP-20240501',
    '2025-05-10', '2025-05-13', '2025-05-14', 'in_transit',
    'Giao buổi sáng', 'active', 2, '2025-05-10 10:56:55', 5, '2025-05-11 00:56:55'
);
INSERT INTO Shipment (
    OrderID, CarrierID, ShipFromLocationID, ShipToAddressID, ShipmentNumber,
    ShippedDate, ExpectedArrivalDate, ActualArrivalDate, ShipmentStatus,
    Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    2, 4, 1, 8, 'SHP-20240502',
    '2025-04-30', '2025-05-02', '2025-05-01', 'pending',
    NULL, 'active', 4, '2025-04-30 14:56:55', 2, '2025-05-02 11:56:55'
);
INSERT INTO Shipment (
    OrderID, CarrierID, ShipFromLocationID, ShipToAddressID, ShipmentNumber,
    ShippedDate, ExpectedArrivalDate, ActualArrivalDate, ShipmentStatus,
    Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    2, 3, 5, 8, 'SHP-20240503',
    '2025-04-30', '2025-05-02', '2025-05-02', 'in_transit',
    'Giao buổi sáng', 'active', 3, '2025-04-30 14:56:55', 3, '2025-05-02 11:56:55'
);
INSERT INTO Shipment (
    OrderID, CarrierID, ShipFromLocationID, ShipToAddressID, ShipmentNumber,
    ShippedDate, ExpectedArrivalDate, ActualArrivalDate, ShipmentStatus,
    Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    3, 2, 2, 7, 'SHP-20240504',
    '2025-04-29', '2025-04-30', '2025-04-30', 'pending',
    'Giao buổi sáng', 'active', 4, '2025-04-29 07:56:55', 2, '2025-04-30 03:56:55'
);
INSERT INTO Shipment (
    OrderID, CarrierID, ShipFromLocationID, ShipToAddressID, ShipmentNumber,
    ShippedDate, ExpectedArrivalDate, ActualArrivalDate, ShipmentStatus,
    Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    4, 2, 4, 6, 'SHP-20240505',
    '2025-05-01', '2025-05-04', '2025-05-05', 'pending',
    NULL, 'active', 4, '2025-05-01 20:56:55', 2, '2025-05-03 10:56:55'
);
INSERT INTO Shipment (
    OrderID, CarrierID, ShipFromLocationID, ShipToAddressID, ShipmentNumber,
    ShippedDate, ExpectedArrivalDate, ActualArrivalDate, ShipmentStatus,
    Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    6, 4, 5, 1, 'SHP-20240506',
    '2025-05-03', '2025-05-04', '2025-05-05', 'in_transit',
    'Giao buổi sáng', 'active', 5, '2025-05-03 23:56:55', 2, '2025-05-05 02:56:55'
);
INSERT INTO Shipment (
    OrderID, CarrierID, ShipFromLocationID, ShipToAddressID, ShipmentNumber,
    ShippedDate, ExpectedArrivalDate, ActualArrivalDate, ShipmentStatus,
    Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    7, 3, 5, 6, 'SHP-20240507',
    '2025-05-02', '2025-05-03', '2025-05-04', 'cancelled',
    'Giao buổi sáng', 'active', 2, '2025-05-02 04:56:55', 4, '2025-05-03 23:56:55'
);
INSERT INTO Shipment (
    OrderID, CarrierID, ShipFromLocationID, ShipToAddressID, ShipmentNumber,
    ShippedDate, ExpectedArrivalDate, ActualArrivalDate, ShipmentStatus,
    Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    9, 5, 4, 10, 'SHP-20240508',
    '2025-04-30', '2025-05-03', '2025-05-04', 'delivered',
    'Giao buổi sáng', 'active', 5, '2025-04-30 00:56:55', 4, '2025-04-30 12:56:55'
);
INSERT INTO Shipment (
    OrderID, CarrierID, ShipFromLocationID, ShipToAddressID, ShipmentNumber,
    ShippedDate, ExpectedArrivalDate, ActualArrivalDate, ShipmentStatus,
    Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    12, 2, 2, 3, 'SHP-20240509',
    '2025-05-07', '2025-05-10', '2025-05-11', 'pending',
    NULL, 'active', 3, '2025-05-07 22:56:55', 5, '2025-05-08 10:56:55'
);
INSERT INTO Shipment (
    OrderID, CarrierID, ShipFromLocationID, ShipToAddressID, ShipmentNumber,
    ShippedDate, ExpectedArrivalDate, ActualArrivalDate, ShipmentStatus,
    Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    13, 5, 4, 8, 'SHP-20240510',
    '2025-05-06', '2025-05-08', '2025-05-08', 'pending',
    'Giao buổi sáng', 'active', 4, '2025-05-06 13:56:55', 4, '2025-05-08 02:56:55'
);
INSERT INTO Shipment (
    OrderID, CarrierID, ShipFromLocationID, ShipToAddressID, ShipmentNumber,
    ShippedDate, ExpectedArrivalDate, ActualArrivalDate, ShipmentStatus,
    Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    14, 1, 1, 8, 'SHP-20240511',
    '2025-05-02', '2025-05-05', '2025-05-06', 'cancelled',
    NULL, 'active', 4, '2025-05-02 09:56:55', 3, '2025-05-02 14:56:55'
);
INSERT INTO Shipment (
    OrderID, CarrierID, ShipFromLocationID, ShipToAddressID, ShipmentNumber,
    ShippedDate, ExpectedArrivalDate, ActualArrivalDate, ShipmentStatus,
    Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    15, 3, 2, 4, 'SHP-20240512',
    '2025-05-08', '2025-05-09', '2025-05-08', 'cancelled',
    NULL, 'active', 5, '2025-05-08 03:56:55', 4, '2025-05-08 06:56:55'
);
INSERT INTO Shipment (
    OrderID, CarrierID, ShipFromLocationID, ShipToAddressID, ShipmentNumber,
    ShippedDate, ExpectedArrivalDate, ActualArrivalDate, ShipmentStatus,
    Notes, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    16, 1, 4, 10, 'SHP-20240513',
    '2025-04-29', '2025-04-30', '2025-05-01', 'pending',
    'Giao buổi sáng', 'active', 2, '2025-04-29 03:56:55', 1, '2025-04-29 17:56:55'
);