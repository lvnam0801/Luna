DELETE FROM PickingTask;
ALTER TABLE PickingTask AUTO_INCREMENT = 1;

INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    1, 1, 3, 'PICK-0001',
    26, 4, '2025-05-10', 'completed',
    4, '2025-05-10 10:56:55', 1, '2025-05-11 00:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    2, 1, 1, 'PICK-0002',
    32, 3, '2025-05-10', 'completed',
    3, '2025-05-10 10:56:55', 3, '2025-05-11 00:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    4, 4, 4, 'PICK-0003',
    33, 5, '2025-05-10', 'completed',
    5, '2025-05-10 10:56:55', 5, '2025-05-11 00:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    5, 1, 3, 'PICK-0004',
    11, 3, '2025-04-30', 'completed',
    3, '2025-04-30 14:56:55', 4, '2025-05-02 11:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    6, 13, 3, 'PICK-0005',
    55, 4, '2025-04-30', 'completed',
    4, '2025-04-30 14:56:55', 1, '2025-05-02 11:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    7, 2, 4, 'PICK-0006',
    13, 1, '2025-04-30', 'completed',
    1, '2025-04-30 14:56:55', 5, '2025-05-02 11:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    8, 19, 5, 'PICK-0007',
    59, 1, '2025-04-30', 'completed',
    1, '2025-04-30 14:56:55', 4, '2025-05-02 11:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    9, 18, 3, 'PICK-0008',
    39, 5, '2025-04-29', 'completed',
    5, '2025-04-29 07:56:55', 5, '2025-04-30 03:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    12, 9, 4, 'PICK-0009',
    33, 1, '2025-05-01', 'completed',
    1, '2025-05-01 20:56:55', 3, '2025-05-03 10:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    13, 32, 4, 'PICK-0010',
    41, 3, '2025-05-01', 'completed',
    3, '2025-05-01 20:56:55', 5, '2025-05-03 10:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    18, 2, 2, 'PICK-0011',
    11, 5, '2025-05-03', 'completed',
    5, '2025-05-03 23:56:55', 2, '2025-05-05 02:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    19, 4, 2, 'PICK-0012',
    21, 2, '2025-05-03', 'completed',
    2, '2025-05-03 23:56:55', 2, '2025-05-05 02:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    20, 3, 1, 'PICK-0013',
    13, 1, '2025-05-02', 'completed',
    1, '2025-05-02 04:56:55', 1, '2025-05-03 23:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    27, 33, 4, 'PICK-0014',
    46, 1, '2025-04-30', 'completed',
    1, '2025-04-30 00:56:55', 4, '2025-04-30 12:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    35, 5, 5, 'PICK-0015',
    14, 4, '2025-05-07', 'completed',
    4, '2025-05-07 22:56:55', 5, '2025-05-08 10:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    36, 5, 5, 'PICK-0016',
    10, 4, '2025-05-07', 'completed',
    4, '2025-05-07 22:56:55', 2, '2025-05-08 10:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    37, 7, 5, 'PICK-0017',
    12, 5, '2025-05-07', 'completed',
    5, '2025-05-07 22:56:55', 4, '2025-05-08 10:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    39, 6, 5, 'PICK-0018',
    9, 3, '2025-05-06', 'completed',
    3, '2025-05-06 13:56:55', 2, '2025-05-08 02:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    42, 7, 1, 'PICK-0019',
    13, 1, '2025-05-02', 'completed',
    1, '2025-05-02 09:56:55', 3, '2025-05-02 14:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    43, 38, 3, 'PICK-0020',
    40, 1, '2025-05-02', 'completed',
    1, '2025-05-02 09:56:55', 1, '2025-05-02 14:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    46, 8, 5, 'PICK-0021',
    19, 5, '2025-05-08', 'completed',
    5, '2025-05-08 03:56:55', 3, '2025-05-08 06:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    47, 11, 3, 'PICK-0022',
    30, 5, '2025-04-29', 'completed',
    5, '2025-04-29 03:56:55', 4, '2025-04-29 17:56:55'
);
INSERT INTO PickingTask (
    OrderLineItemID, SKUItemID, PickFromLocationID, PickingNumber,
    PickedQuantity, PickedBy, PickedDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    50, 23, 1, 'PICK-0023',
    35, 1, '2025-04-29', 'completed',
    1, '2025-04-29 03:56:55', 1, '2025-04-29 17:56:55'
);