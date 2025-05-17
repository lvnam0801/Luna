DELETE FROM ExportOrderLineItem;
ALTER TABLE ExportOrderLineItem AUTO_INCREMENT = 1;

INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    1, 5, 'EX-LN-0001', 26, NULL,
    586420, 'shipped', 4, '2025-05-10 10:56:55',
    1, '2025-05-11 00:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    1, 3, 'EX-LN-0002', 32, 'Lô-5765',
    233859, 'packed', 3, '2025-05-10 10:56:55',
    3, '2025-05-11 00:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    1, 2, 'EX-LN-0003', 88, NULL,
    69263, 'pending', 4, '2025-05-10 10:56:55',
    2, '2025-05-11 00:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    1, 2, 'EX-LN-0004', 33, 'Lô-1912',
    547651, 'pending', 5, '2025-05-10 10:56:55',
    5, '2025-05-11 00:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    2, 3, 'EX-LN-0005', 11, 'Lô-4473',
    564184, 'pending', 3, '2025-04-30 14:56:55',
    4, '2025-05-02 11:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    2, 2, 'EX-LN-0006', 55, 'Lô-7905',
    815499, 'shipped', 4, '2025-04-30 14:56:55',
    1, '2025-05-02 11:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    2, 7, 'EX-LN-0007', 13, 'Lô-6417',
    643588, 'picked', 1, '2025-04-30 14:56:55',
    5, '2025-05-02 11:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    2, 2, 'EX-LN-0008', 59, 'Lô-6689',
    513587, 'shipped', 1, '2025-04-30 14:56:55',
    4, '2025-05-02 11:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    3, 3, 'EX-LN-0009', 39, 'Lô-5702',
    120268, 'picked', 5, '2025-04-29 07:56:55',
    5, '2025-04-30 03:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    3, 9, 'EX-LN-0010', 92, 'Lô-2993',
    316239, 'picked', 1, '2025-04-29 07:56:55',
    4, '2025-04-30 03:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    4, 8, 'EX-LN-0011', 58, 'Lô-7258',
    914844, 'picked', 1, '2025-05-01 20:56:55',
    5, '2025-05-03 10:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    4, 7, 'EX-LN-0012', 33, 'Lô-4915',
    460899, 'picked', 1, '2025-05-01 20:56:55',
    3, '2025-05-03 10:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    4, 3, 'EX-LN-0013', 41, NULL,
    973195, 'packed', 3, '2025-05-01 20:56:55',
    5, '2025-05-03 10:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    4, 2, 'EX-LN-0014', 81, 'Lô-7649',
    693933, 'cancelled', 4, '2025-05-01 20:56:55',
    3, '2025-05-03 10:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    5, 4, 'EX-LN-0015', 75, 'Lô-7483',
    250418, 'cancelled', 1, '2025-05-04 08:56:55',
    2, '2025-05-04 10:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    5, 8, 'EX-LN-0016', 67, 'Lô-6532',
    280104, 'picked', 2, '2025-05-04 08:56:55',
    1, '2025-05-04 10:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    6, 9, 'EX-LN-0017', 69, NULL,
    303640, 'shipped', 2, '2025-05-03 23:56:55',
    4, '2025-05-05 02:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    6, 10, 'EX-LN-0018', 11, 'Lô-1755',
    638395, 'picked', 5, '2025-05-03 23:56:55',
    2, '2025-05-05 02:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    6, 2, 'EX-LN-0019', 21, 'Lô-6088',
    450138, 'packed', 2, '2025-05-03 23:56:55',
    2, '2025-05-05 02:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    7, 9, 'EX-LN-0020', 13, NULL,
    77791, 'picked', 1, '2025-05-02 04:56:55',
    1, '2025-05-03 23:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    7, 6, 'EX-LN-0021', 98, 'Lô-8526',
    442259, 'shipped', 1, '2025-05-02 04:56:55',
    2, '2025-05-03 23:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    8, 6, 'EX-LN-0022', 98, 'Lô-8935',
    89531, 'pending', 4, '2025-05-09 08:56:55',
    5, '2025-05-09 15:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    8, 4, 'EX-LN-0023', 53, 'Lô-7429',
    614522, 'cancelled', 5, '2025-05-09 08:56:55',
    2, '2025-05-09 15:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    8, 7, 'EX-LN-0024', 87, 'Lô-8763',
    700555, 'shipped', 2, '2025-05-09 08:56:55',
    4, '2025-05-09 15:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    8, 7, 'EX-LN-0025', 69, 'Lô-7844',
    698154, 'picked', 2, '2025-05-09 08:56:55',
    4, '2025-05-09 15:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    9, 6, 'EX-LN-0026', 98, NULL,
    736094, 'cancelled', 1, '2025-04-30 00:56:55',
    5, '2025-04-30 12:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    9, 3, 'EX-LN-0027', 46, NULL,
    839280, 'shipped', 1, '2025-04-30 00:56:55',
    4, '2025-04-30 12:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    9, 7, 'EX-LN-0028', 93, 'Lô-6335',
    948244, 'cancelled', 5, '2025-04-30 00:56:55',
    2, '2025-04-30 12:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    9, 7, 'EX-LN-0029', 61, 'Lô-1815',
    953107, 'packed', 3, '2025-04-30 00:56:55',
    2, '2025-04-30 12:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    10, 4, 'EX-LN-0030', 86, NULL,
    984792, 'picked', 3, '2025-04-30 20:56:55',
    5, '2025-05-01 21:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    10, 5, 'EX-LN-0031', 59, 'Lô-9497',
    918181, 'pending', 3, '2025-04-30 20:56:55',
    2, '2025-05-01 21:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    11, 10, 'EX-LN-0032', 50, 'Lô-2698',
    470241, 'shipped', 4, '2025-04-28 23:56:55',
    3, '2025-04-29 10:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    11, 6, 'EX-LN-0033', 72, 'Lô-8961',
    127899, 'shipped', 1, '2025-04-28 23:56:55',
    5, '2025-04-29 10:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    11, 3, 'EX-LN-0034', 58, 'Lô-3331',
    556694, 'pending', 1, '2025-04-28 23:56:55',
    3, '2025-04-29 10:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    12, 4, 'EX-LN-0035', 14, 'Lô-9382',
    623968, 'cancelled', 4, '2025-05-07 22:56:55',
    5, '2025-05-08 10:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    12, 7, 'EX-LN-0036', 10, NULL,
    318668, 'pending', 4, '2025-05-07 22:56:55',
    2, '2025-05-08 10:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    12, 4, 'EX-LN-0037', 12, NULL,
    530797, 'pending', 5, '2025-05-07 22:56:55',
    4, '2025-05-08 10:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    13, 3, 'EX-LN-0038', 78, 'Lô-1708',
    692008, 'packed', 3, '2025-05-06 13:56:55',
    4, '2025-05-08 02:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    13, 3, 'EX-LN-0039', 9, 'Lô-4535',
    771336, 'cancelled', 3, '2025-05-06 13:56:55',
    2, '2025-05-08 02:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    13, 10, 'EX-LN-0040', 53, 'Lô-5792',
    714415, 'picked', 3, '2025-05-06 13:56:55',
    3, '2025-05-08 02:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    13, 8, 'EX-LN-0041', 96, 'Lô-3521',
    438768, 'pending', 3, '2025-05-06 13:56:55',
    3, '2025-05-08 02:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    14, 3, 'EX-LN-0042', 13, 'Lô-3826',
    759689, 'shipped', 1, '2025-05-02 09:56:55',
    3, '2025-05-02 14:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    14, 7, 'EX-LN-0043', 40, NULL,
    266396, 'picked', 1, '2025-05-02 09:56:55',
    1, '2025-05-02 14:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    15, 1, 'EX-LN-0044', 71, 'Lô-6248',
    111338, 'shipped', 4, '2025-05-08 03:56:55',
    2, '2025-05-08 06:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    15, 4, 'EX-LN-0045', 90, 'Lô-5783',
    80157, 'picked', 4, '2025-05-08 03:56:55',
    4, '2025-05-08 06:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    15, 4, 'EX-LN-0046', 19, NULL,
    141329, 'picked', 5, '2025-05-08 03:56:55',
    3, '2025-05-08 06:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    16, 10, 'EX-LN-0047', 30, 'Lô-5750',
    961243, 'pending', 5, '2025-04-29 03:56:55',
    4, '2025-04-29 17:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    16, 3, 'EX-LN-0048', 85, 'Lô-4543',
    676937, 'pending', 3, '2025-04-29 03:56:55',
    2, '2025-04-29 17:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    16, 7, 'EX-LN-0049', 92, 'Lô-6190',
    607411, 'packed', 5, '2025-04-29 03:56:55',
    3, '2025-04-29 17:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    16, 3, 'EX-LN-0050', 35, NULL,
    101784, 'picked', 1, '2025-04-29 03:56:55',
    1, '2025-04-29 17:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    17, 3, 'EX-LN-0051', 81, NULL,
    864648, 'picked', 2, '2025-05-05 10:56:55',
    5, '2025-05-07 09:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    17, 10, 'EX-LN-0052', 85, NULL,
    868841, 'packed', 3, '2025-05-05 10:56:55',
    2, '2025-05-07 09:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    18, 10, 'EX-LN-0053', 38, 'Lô-4721',
    851511, 'packed', 3, '2025-05-08 23:56:55',
    1, '2025-05-10 08:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    18, 6, 'EX-LN-0054', 96, 'Lô-4677',
    65717, 'cancelled', 1, '2025-05-08 23:56:55',
    5, '2025-05-10 08:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    18, 9, 'EX-LN-0055', 82, 'Lô-8093',
    237361, 'picked', 1, '2025-05-08 23:56:55',
    1, '2025-05-10 08:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    18, 9, 'EX-LN-0056', 76, 'Lô-1325',
    269422, 'picked', 4, '2025-05-08 23:56:55',
    5, '2025-05-10 08:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    19, 1, 'EX-LN-0057', 66, 'Lô-7111',
    275001, 'packed', 4, '2025-05-09 18:56:55',
    4, '2025-05-10 23:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    19, 10, 'EX-LN-0058', 77, 'Lô-2953',
    731996, 'packed', 1, '2025-05-09 18:56:55',
    1, '2025-05-10 23:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    19, 6, 'EX-LN-0059', 49, NULL,
    416766, 'cancelled', 1, '2025-05-09 18:56:55',
    2, '2025-05-10 23:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    19, 8, 'EX-LN-0060', 73, NULL,
    219872, 'shipped', 2, '2025-05-09 18:56:55',
    3, '2025-05-10 23:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    20, 1, 'EX-LN-0061', 42, NULL,
    978608, 'cancelled', 1, '2025-05-02 05:56:55',
    2, '2025-05-04 01:56:55'
);
INSERT INTO ExportOrderLineItem (
    OrderID, ProductID, LineItemNumber, ExportedQuantity, LotNumber,
    UnitPrice, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    20, 3, 'EX-LN-0062', 37, 'Lô-5985',
    963733, 'picked', 3, '2025-05-02 05:56:55',
    3, '2025-05-04 01:56:55'
);