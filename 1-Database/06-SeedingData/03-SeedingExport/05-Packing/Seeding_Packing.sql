DELETE FROM Packing;
ALTER TABLE Packing AUTO_INCREMENT = 1;

INSERT INTO Packing (
    OrderID, PackToLocationID, PackingNumber, PackedBy, PackedDate,
    Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    1, 3, 'PACK-0001', 2, '2025-05-10',
    'completed', 2, '2025-05-10 10:56:55', 5, '2025-05-11 00:56:55'
);
INSERT INTO Packing (
    OrderID, PackToLocationID, PackingNumber, PackedBy, PackedDate,
    Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    2, 1, 'PACK-0002', 4, '2025-04-30',
    'completed', 4, '2025-04-30 14:56:55', 2, '2025-05-02 11:56:55'
);
INSERT INTO Packing (
    OrderID, PackToLocationID, PackingNumber, PackedBy, PackedDate,
    Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    2, 5, 'PACK-0003', 3, '2025-04-30',
    'completed', 3, '2025-04-30 14:56:55', 3, '2025-05-02 11:56:55'
);
INSERT INTO Packing (
    OrderID, PackToLocationID, PackingNumber, PackedBy, PackedDate,
    Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    3, 2, 'PACK-0004', 4, '2025-04-29',
    'completed', 4, '2025-04-29 07:56:55', 2, '2025-04-30 03:56:55'
);
INSERT INTO Packing (
    OrderID, PackToLocationID, PackingNumber, PackedBy, PackedDate,
    Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    4, 4, 'PACK-0005', 4, '2025-05-01',
    'completed', 4, '2025-05-01 20:56:55', 2, '2025-05-03 10:56:55'
);
INSERT INTO Packing (
    OrderID, PackToLocationID, PackingNumber, PackedBy, PackedDate,
    Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    6, 5, 'PACK-0006', 5, '2025-05-03',
    'completed', 5, '2025-05-03 23:56:55', 2, '2025-05-05 02:56:55'
);
INSERT INTO Packing (
    OrderID, PackToLocationID, PackingNumber, PackedBy, PackedDate,
    Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    7, 5, 'PACK-0007', 2, '2025-05-02',
    'completed', 2, '2025-05-02 04:56:55', 4, '2025-05-03 23:56:55'
);
INSERT INTO Packing (
    OrderID, PackToLocationID, PackingNumber, PackedBy, PackedDate,
    Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    9, 4, 'PACK-0008', 5, '2025-04-30',
    'completed', 5, '2025-04-30 00:56:55', 4, '2025-04-30 12:56:55'
);
INSERT INTO Packing (
    OrderID, PackToLocationID, PackingNumber, PackedBy, PackedDate,
    Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    12, 2, 'PACK-0009', 3, '2025-05-07',
    'completed', 3, '2025-05-07 22:56:55', 5, '2025-05-08 10:56:55'
);
INSERT INTO Packing (
    OrderID, PackToLocationID, PackingNumber, PackedBy, PackedDate,
    Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    13, 4, 'PACK-0010', 4, '2025-05-06',
    'completed', 4, '2025-05-06 13:56:55', 4, '2025-05-08 02:56:55'
);
INSERT INTO Packing (
    OrderID, PackToLocationID, PackingNumber, PackedBy, PackedDate,
    Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    14, 1, 'PACK-0011', 4, '2025-05-02',
    'completed', 4, '2025-05-02 09:56:55', 3, '2025-05-02 14:56:55'
);
INSERT INTO Packing (
    OrderID, PackToLocationID, PackingNumber, PackedBy, PackedDate,
    Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    15, 2, 'PACK-0012', 5, '2025-05-08',
    'completed', 5, '2025-05-08 03:56:55', 4, '2025-05-08 06:56:55'
);
INSERT INTO Packing (
    OrderID, PackToLocationID, PackingNumber, PackedBy, PackedDate,
    Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    16, 4, 'PACK-0013', 2, '2025-04-29',
    'completed', 2, '2025-04-29 03:56:55', 1, '2025-04-29 17:56:55'
);