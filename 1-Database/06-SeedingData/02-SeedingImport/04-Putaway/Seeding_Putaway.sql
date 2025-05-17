DELETE FROM Putaway;
ALTER TABLE Putaway AUTO_INCREMENT = 1;

INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    7, 5, 1, 'PUT-0001',
    69, 'stored', 3, '2025-04-29', 'completed',
    3, '2025-04-28 21:16:50', 2, '2025-04-30 12:16:50'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    8, 1, 2, 'PUT-0002',
    24, 'stored', 5, '2025-04-30', 'completed',
    2, '2025-04-28 08:03:17', 5, '2025-04-30 07:03:17'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    10, 1, 3, 'PUT-0003',
    18, 'stored', 3, '2025-05-09', 'completed',
    1, '2025-05-09 20:39:56', 3, '2025-05-11 12:39:56'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    12, 4, 4, 'PUT-0004',
    62, 'quarantined', 2, '2025-05-08', 'completed',
    5, '2025-05-07 22:23:30', 3, '2025-05-09 17:23:30'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    14, 5, 5, 'PUT-0005',
    27, 'stored', 2, '2025-04-30', 'completed',
    5, '2025-04-29 12:29:31', 5, '2025-04-30 03:29:31'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    15, 3, 6, 'PUT-0006',
    11, 'quarantined', 2, '2025-05-06', 'completed',
    1, '2025-05-05 04:36:15', 5, '2025-05-05 07:36:15'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    17, 5, 7, 'PUT-0007',
    30, 'quarantined', 1, '2025-05-08', 'completed',
    3, '2025-05-08 05:31:01', 1, '2025-05-09 05:31:01'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    18, 4, 8, 'PUT-0008',
    25, 'quarantined', 2, '2025-04-30', 'completed',
    2, '2025-04-30 21:48:09', 3, '2025-05-01 15:48:09'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    21, 4, 9, 'PUT-0009',
    38, 'stored', 5, '2025-05-04', 'completed',
    5, '2025-05-04 23:49:05', 4, '2025-05-06 16:49:05'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    24, 3, 10, 'PUT-0010',
    1, 'stored', 3, '2025-05-08', 'completed',
    1, '2025-05-07 05:51:02', 1, '2025-05-08 19:51:02'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    25, 4, 11, 'PUT-0011',
    33, 'stored', 4, '2025-05-12', 'completed',
    3, '2025-05-10 05:13:11', 5, '2025-05-11 20:13:11'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    28, 1, 12, 'PUT-0012',
    26, 'stored', 2, '2025-05-03', 'completed',
    4, '2025-05-03 03:59:33', 4, '2025-05-04 10:59:33'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    29, 2, 13, 'PUT-0013',
    68, 'stored', 5, '2025-05-13', 'completed',
    3, '2025-05-11 02:06:49', 2, '2025-05-12 18:06:49'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    33, 3, 14, 'PUT-0014',
    21, 'stored', 1, '2025-05-08', 'completed',
    5, '2025-05-08 05:13:18', 2, '2025-05-08 18:13:18'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    34, 4, 15, 'PUT-0015',
    28, 'stored', 4, '2025-05-09', 'completed',
    4, '2025-05-07 09:52:51', 2, '2025-05-08 16:52:51'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    35, 4, 16, 'PUT-0016',
    3, 'stored', 2, '2025-05-06', 'completed',
    3, '2025-05-04 05:39:05', 2, '2025-05-05 15:39:05'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    36, 5, 17, 'PUT-0017',
    20, 'quarantined', 5, '2025-05-01', 'completed',
    5, '2025-04-29 10:04:34', 5, '2025-04-29 14:04:34'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    41, 5, 18, 'PUT-0018',
    41, 'quarantined', 3, '2025-04-30', 'completed',
    2, '2025-04-29 15:30:33', 1, '2025-04-30 20:30:33'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    42, 5, 19, 'PUT-0019',
    74, 'stored', 3, '2025-05-08', 'completed',
    2, '2025-05-07 09:59:35', 2, '2025-05-07 19:59:35'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    44, 1, 20, 'PUT-0020',
    6, 'stored', 1, '2025-05-06', 'completed',
    1, '2025-05-05 11:09:20', 3, '2025-05-06 10:09:20'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    45, 1, 21, 'PUT-0021',
    11, 'quarantined', 2, '2025-05-05', 'completed',
    4, '2025-05-04 17:15:53', 5, '2025-05-06 08:15:53'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    46, 2, 22, 'PUT-0022',
    26, 'quarantined', 1, '2025-05-01', 'completed',
    4, '2025-05-01 08:18:32', 4, '2025-05-01 18:18:32'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    47, 2, 23, 'PUT-0023',
    36, 'quarantined', 2, '2025-05-05', 'completed',
    3, '2025-05-03 11:26:26', 2, '2025-05-04 12:26:26'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    50, 4, 24, 'PUT-0024',
    19, 'stored', 2, '2025-05-08', 'completed',
    4, '2025-05-08 22:48:56', 4, '2025-05-09 08:48:56'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    54, 5, 25, 'PUT-0025',
    21, 'stored', 3, '2025-04-29', 'completed',
    5, '2025-04-28 22:11:23', 2, '2025-04-29 18:11:23'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    55, 4, 26, 'PUT-0026',
    9, 'stored', 4, '2025-05-10', 'completed',
    1, '2025-05-08 02:18:41', 2, '2025-05-09 02:18:41'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    60, 5, 27, 'PUT-0027',
    18, 'stored', 2, '2025-05-08', 'completed',
    3, '2025-05-08 01:53:31', 2, '2025-05-09 10:53:31'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    62, 4, 28, 'PUT-0028',
    16, 'stored', 5, '2025-05-06', 'completed',
    3, '2025-05-06 12:36:08', 4, '2025-05-08 11:36:08'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    63, 1, 29, 'PUT-0029',
    4, 'stored', 5, '2025-05-07', 'completed',
    5, '2025-05-05 15:31:59', 3, '2025-05-05 18:31:59'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    64, 4, 30, 'PUT-0030',
    19, 'stored', 4, '2025-04-28', 'completed',
    4, '2025-04-27 16:30:40', 1, '2025-04-29 11:30:40'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    66, 2, 31, 'PUT-0031',
    3, 'stored', 2, '2025-05-05', 'completed',
    5, '2025-05-04 21:07:18', 5, '2025-05-05 09:07:18'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    67, 2, 32, 'PUT-0032',
    43, 'quarantined', 3, '2025-05-06', 'completed',
    5, '2025-05-04 12:46:42', 5, '2025-05-05 22:46:42'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    69, 2, 33, 'PUT-0033',
    49, 'quarantined', 3, '2025-05-11', 'completed',
    4, '2025-05-10 08:34:35', 4, '2025-05-10 17:34:35'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    70, 3, 34, 'PUT-0034',
    28, 'quarantined', 3, '2025-05-12', 'completed',
    1, '2025-05-10 03:50:56', 3, '2025-05-11 15:50:56'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    71, 5, 35, 'PUT-0035',
    28, 'quarantined', 2, '2025-05-01', 'completed',
    4, '2025-05-01 00:22:58', 2, '2025-05-02 23:22:58'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    72, 3, 36, 'PUT-0036',
    23, 'stored', 2, '2025-05-04', 'completed',
    2, '2025-05-04 01:32:03', 2, '2025-05-04 21:32:03'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    74, 3, 37, 'PUT-0037',
    5, 'stored', 2, '2025-05-09', 'completed',
    3, '2025-05-08 09:08:41', 3, '2025-05-09 04:08:41'
);
INSERT INTO Putaway (
    ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayNumber,
    Quantity, PutawayResult, PutawayBy, PutawayDate, Status,
    CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    75, 4, 38, 'PUT-0038',
    44, 'stored', 1, '2025-04-27', 'completed',
    2, '2025-04-27 16:27:10', 1, '2025-04-28 01:27:10'
);