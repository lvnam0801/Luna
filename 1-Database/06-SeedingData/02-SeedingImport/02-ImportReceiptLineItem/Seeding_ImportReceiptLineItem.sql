DELETE FROM ImportReceiptLineItem;
ALTER TABLE ImportReceiptLineItem AUTO_INCREMENT = 1;

INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    1, 9, 'DMMH-0001', 41, 'LOT-3604',
    '2025-10-31', 430535, 'inspected', 2, '2025-05-03 19:35:48', 3, '2025-05-05 12:35:48'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    1, 6, 'DMMH-0002', 78, 'LOT-1173',
    '2026-04-16', 85036, 'completed', 1, '2025-04-28 21:33:39', 1, '2025-04-30 06:33:39'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    1, 7, 'DMMH-0003', 11, 'LOT-1939',
    '2026-04-03', 464200, 'completed', 3, '2025-05-04 05:25:40', 1, '2025-05-04 17:25:40'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    2, 1, 'DMMH-0004', 45, 'LOT-9597',
    '2025-07-02', 103391, 'cancelled', 4, '2025-05-10 21:35:46', 3, '2025-05-11 23:35:46'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    2, 1, 'DMMH-0005', 17, 'LOT-7180',
    '2025-08-22', 138992, 'inspected', 3, '2025-05-08 21:53:25', 1, '2025-05-10 18:53:25'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    2, 8, 'DMMH-0006', 52, 'LOT-3261',
    '2025-06-13', 364228, 'inspected', 4, '2025-05-08 13:30:22', 1, '2025-05-08 23:30:22'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    2, 3, 'DMMH-0007', 86, 'LOT-2330',
    '2025-12-28', 375092, 'pending', 3, '2025-04-28 21:16:50', 2, '2025-04-30 12:16:50'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    3, 7, 'DMMH-0008', 36, 'LOT-5614',
    '2025-10-23', 418349, 'partially_putaway', 2, '2025-04-28 08:03:17', 5, '2025-04-30 07:03:17'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    3, 4, 'DMMH-0009', 16, 'LOT-5890',
    '2026-03-05', 488421, 'cancelled', 3, '2025-05-04 15:50:24', 3, '2025-05-05 01:50:24'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    3, 9, 'DMMH-0010', 88, 'LOT-6696',
    '2026-01-12', 216406, 'completed', 1, '2025-05-09 20:39:56', 3, '2025-05-11 12:39:56'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    3, 9, 'DMMH-0011', 64, 'LOT-5824',
    '2026-02-11', 267770, 'inspected', 2, '2025-05-08 05:42:02', 1, '2025-05-09 23:42:02'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    3, 1, 'DMMH-0012', 84, 'LOT-7619',
    '2025-10-16', 73442, 'inspected', 5, '2025-05-07 22:23:30', 3, '2025-05-09 17:23:30'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    4, 5, 'DMMH-0013', 46, 'LOT-7387',
    '2026-01-30', 328994, 'partially_putaway', 2, '2025-05-01 18:58:32', 2, '2025-05-03 12:58:32'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    4, 9, 'DMMH-0014', 90, 'LOT-9492',
    '2026-05-01', 438509, 'pending', 5, '2025-04-29 12:29:31', 5, '2025-04-30 03:29:31'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    4, 7, 'DMMH-0015', 27, 'LOT-1654',
    '2025-07-05', 88591, 'completed', 1, '2025-05-05 04:36:15', 5, '2025-05-05 07:36:15'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    4, 3, 'DMMH-0016', 34, 'LOT-2279',
    '2025-11-08', 450150, 'pending', 5, '2025-05-07 14:45:13', 4, '2025-05-08 01:45:13'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    5, 8, 'DMMH-0017', 69, 'LOT-6637',
    '2026-02-12', 444000, 'cancelled', 3, '2025-05-08 05:31:01', 1, '2025-05-09 05:31:01'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    5, 6, 'DMMH-0018', 37, 'LOT-3316',
    '2026-01-19', 417744, 'pending', 2, '2025-04-30 21:48:09', 3, '2025-05-01 15:48:09'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    5, 3, 'DMMH-0019', 98, 'LOT-8083',
    '2025-11-10', 138421, 'pending', 4, '2025-04-27 21:24:24', 1, '2025-04-29 12:24:24'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    5, 9, 'DMMH-0020', 81, 'LOT-7449',
    '2026-05-06', 50584, 'completed', 4, '2025-05-08 04:16:59', 2, '2025-05-09 21:16:59'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    5, 8, 'DMMH-0021', 74, 'LOT-4080',
    '2025-12-11', 221803, 'completed', 5, '2025-05-04 23:49:05', 4, '2025-05-06 16:49:05'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    6, 8, 'DMMH-0022', 21, 'LOT-2733',
    '2025-06-11', 92878, 'pending', 1, '2025-05-03 11:31:09', 2, '2025-05-05 05:31:09'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    6, 8, 'DMMH-0023', 87, 'LOT-5866',
    '2025-08-27', 187383, 'pending', 5, '2025-04-29 08:54:23', 1, '2025-04-29 17:54:23'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    6, 6, 'DMMH-0024', 41, 'LOT-6007',
    '2026-03-09', 475930, 'pending', 1, '2025-05-07 05:51:02', 1, '2025-05-08 19:51:02'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    7, 5, 'DMMH-0025', 77, 'LOT-5988',
    '2025-08-26', 499832, 'inspected', 3, '2025-05-10 05:13:11', 5, '2025-05-11 20:13:11'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    7, 10, 'DMMH-0026', 35, 'LOT-5182',
    '2025-11-11', 230604, 'completed', 1, '2025-05-04 23:25:03', 5, '2025-05-06 01:25:03'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    7, 2, 'DMMH-0027', 65, 'LOT-4830',
    '2025-06-13', 257224, 'completed', 2, '2025-05-06 12:55:58', 1, '2025-05-07 13:55:58'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    7, 8, 'DMMH-0028', 45, 'LOT-1963',
    '2025-10-10', 434910, 'cancelled', 4, '2025-05-03 03:59:33', 4, '2025-05-04 10:59:33'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    7, 7, 'DMMH-0029', 93, 'LOT-9125',
    '2025-12-24', 318945, 'inspected', 3, '2025-05-11 02:06:49', 2, '2025-05-12 18:06:49'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    8, 1, 'DMMH-0030', 20, 'LOT-8729',
    '2025-09-02', 476166, 'inspected', 1, '2025-05-03 19:07:12', 2, '2025-05-04 05:07:12'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    8, 5, 'DMMH-0031', 50, 'LOT-2249',
    '2026-01-07', 366402, 'inspected', 3, '2025-05-06 23:12:37', 4, '2025-05-08 22:12:37'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    8, 4, 'DMMH-0032', 72, 'LOT-2496',
    '2025-08-25', 462364, 'cancelled', 1, '2025-05-10 13:44:08', 1, '2025-05-10 22:44:08'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    8, 5, 'DMMH-0033', 62, 'LOT-4551',
    '2025-09-14', 234508, 'completed', 5, '2025-05-08 05:13:18', 2, '2025-05-08 18:13:18'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    8, 7, 'DMMH-0034', 84, 'LOT-5632',
    '2026-04-22', 338638, 'cancelled', 4, '2025-05-07 09:52:51', 2, '2025-05-08 16:52:51'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    9, 5, 'DMMH-0035', 73, 'LOT-3872',
    '2025-11-21', 191357, 'inspected', 3, '2025-05-04 05:39:05', 2, '2025-05-05 15:39:05'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    9, 2, 'DMMH-0036', 88, 'LOT-8757',
    '2026-01-11', 217789, 'partially_putaway', 5, '2025-04-29 10:04:34', 5, '2025-04-29 14:04:34'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    9, 2, 'DMMH-0037', 60, 'LOT-6142',
    '2026-01-22', 270942, 'inspected', 5, '2025-05-01 04:49:55', 5, '2025-05-02 12:49:55'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    9, 9, 'DMMH-0038', 84, 'LOT-3212',
    '2025-10-20', 352822, 'partially_putaway', 3, '2025-05-04 14:28:06', 2, '2025-05-06 02:28:06'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    9, 9, 'DMMH-0039', 53, 'LOT-7253',
    '2025-10-06', 404388, 'pending', 3, '2025-04-28 06:46:26', 3, '2025-04-28 15:46:26'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    10, 5, 'DMMH-0040', 98, 'LOT-9987',
    '2026-02-27', 302644, 'inspected', 3, '2025-04-28 15:45:08', 2, '2025-04-29 03:45:08'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    10, 3, 'DMMH-0041', 99, 'LOT-8045',
    '2025-07-29', 196033, 'cancelled', 2, '2025-04-29 15:30:33', 1, '2025-04-30 20:30:33'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    11, 6, 'DMMH-0042', 75, 'LOT-4252',
    '2026-03-08', 215623, 'pending', 2, '2025-05-07 09:59:35', 2, '2025-05-07 19:59:35'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    11, 7, 'DMMH-0043', 27, 'LOT-9627',
    '2025-09-30', 328662, 'completed', 5, '2025-05-01 07:22:07', 1, '2025-05-02 01:22:07'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    11, 6, 'DMMH-0044', 36, 'LOT-7514',
    '2025-08-02', 454058, 'cancelled', 1, '2025-05-05 11:09:20', 3, '2025-05-06 10:09:20'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    12, 5, 'DMMH-0045', 11, 'LOT-6754',
    '2026-02-23', 198797, 'completed', 4, '2025-05-04 17:15:53', 5, '2025-05-06 08:15:53'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    12, 5, 'DMMH-0046', 29, 'LOT-5999',
    '2025-07-05', 197909, 'inspected', 4, '2025-05-01 08:18:32', 4, '2025-05-01 18:18:32'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    12, 2, 'DMMH-0047', 61, 'LOT-5109',
    '2025-06-28', 119814, 'completed', 3, '2025-05-03 11:26:26', 2, '2025-05-04 12:26:26'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    13, 2, 'DMMH-0048', 15, 'LOT-6218',
    '2025-09-07', 466245, 'inspected', 2, '2025-05-02 02:20:43', 4, '2025-05-04 01:20:43'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    13, 6, 'DMMH-0049', 28, 'LOT-2716',
    '2026-05-06', 319601, 'inspected', 4, '2025-05-09 17:24:37', 5, '2025-05-11 05:24:37'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    13, 5, 'DMMH-0050', 42, 'LOT-5191',
    '2025-10-09', 407277, 'completed', 4, '2025-05-08 22:48:56', 4, '2025-05-09 08:48:56'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    13, 1, 'DMMH-0051', 60, 'LOT-3096',
    '2025-12-08', 296272, 'partially_putaway', 1, '2025-04-30 10:47:03', 4, '2025-05-01 05:47:03'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    14, 3, 'DMMH-0052', 37, 'LOT-1713',
    '2025-07-03', 233842, 'pending', 3, '2025-04-30 15:57:36', 5, '2025-05-01 13:57:36'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    14, 4, 'DMMH-0053', 48, 'LOT-9984',
    '2026-01-19', 473638, 'completed', 5, '2025-05-08 09:33:05', 5, '2025-05-10 02:33:05'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    14, 1, 'DMMH-0054', 27, 'LOT-3393',
    '2026-03-22', 392070, 'inspected', 5, '2025-04-28 22:11:23', 2, '2025-04-29 18:11:23'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    15, 1, 'DMMH-0055', 43, 'LOT-9072',
    '2025-08-12', 435926, 'inspected', 1, '2025-05-08 02:18:41', 2, '2025-05-09 02:18:41'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    15, 3, 'DMMH-0056', 47, 'LOT-6122',
    '2026-02-26', 145353, 'partially_putaway', 1, '2025-05-03 08:06:00', 2, '2025-05-04 14:06:00'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    15, 5, 'DMMH-0057', 39, 'LOT-2709',
    '2026-02-01', 97920, 'completed', 5, '2025-05-05 15:34:23', 4, '2025-05-05 17:34:23'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    15, 8, 'DMMH-0058', 16, 'LOT-7266',
    '2026-02-01', 418088, 'cancelled', 1, '2025-05-05 02:10:42', 4, '2025-05-05 21:10:42'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    16, 4, 'DMMH-0059', 77, 'LOT-1486',
    '2025-12-14', 149532, 'inspected', 1, '2025-04-28 18:27:41', 4, '2025-04-29 02:27:41'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    16, 9, 'DMMH-0060', 19, 'LOT-5358',
    '2025-12-20', 288582, 'pending', 3, '2025-05-08 01:53:31', 2, '2025-05-09 10:53:31'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    16, 9, 'DMMH-0061', 58, 'LOT-7520',
    '2026-01-23', 240416, 'completed', 1, '2025-05-05 04:30:18', 4, '2025-05-06 03:30:18'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    16, 2, 'DMMH-0062', 79, 'LOT-4784',
    '2025-08-11', 291958, 'partially_putaway', 3, '2025-05-06 12:36:08', 4, '2025-05-08 11:36:08'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    16, 2, 'DMMH-0063', 87, 'LOT-1922',
    '2026-03-27', 361063, 'cancelled', 5, '2025-05-05 15:31:59', 3, '2025-05-05 18:31:59'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    17, 2, 'DMMH-0064', 41, 'LOT-8169',
    '2025-06-22', 232828, 'cancelled', 4, '2025-04-27 16:30:40', 1, '2025-04-29 11:30:40'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    17, 8, 'DMMH-0065', 74, 'LOT-3896',
    '2026-05-10', 465287, 'pending', 4, '2025-05-05 22:12:45', 3, '2025-05-06 07:12:45'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    18, 4, 'DMMH-0066', 84, 'LOT-4869',
    '2025-11-13', 157882, 'pending', 5, '2025-05-04 21:07:18', 5, '2025-05-05 09:07:18'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    18, 7, 'DMMH-0067', 85, 'LOT-4721',
    '2026-04-27', 395633, 'cancelled', 5, '2025-05-04 12:46:42', 5, '2025-05-05 22:46:42'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    18, 6, 'DMMH-0068', 24, 'LOT-9114',
    '2025-06-27', 438496, 'pending', 5, '2025-05-07 19:18:35', 5, '2025-05-08 14:18:35'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    18, 4, 'DMMH-0069', 91, 'LOT-5573',
    '2025-11-03', 382374, 'partially_putaway', 4, '2025-05-10 08:34:35', 4, '2025-05-10 17:34:35'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    19, 2, 'DMMH-0070', 47, 'LOT-3060',
    '2026-01-01', 81712, 'inspected', 1, '2025-05-10 03:50:56', 3, '2025-05-11 15:50:56'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    19, 7, 'DMMH-0071', 37, 'LOT-7634',
    '2026-01-30', 354251, 'cancelled', 4, '2025-05-01 00:22:58', 2, '2025-05-02 23:22:58'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    19, 6, 'DMMH-0072', 55, 'LOT-2076',
    '2025-11-24', 326514, 'completed', 2, '2025-05-04 01:32:03', 2, '2025-05-04 21:32:03'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    20, 4, 'DMMH-0073', 97, 'LOT-1080',
    '2025-06-10', 357898, 'inspected', 2, '2025-05-08 08:05:47', 1, '2025-05-09 10:05:47'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    20, 9, 'DMMH-0074', 17, 'LOT-6859',
    '2025-11-10', 304125, 'cancelled', 3, '2025-05-08 09:08:41', 3, '2025-05-09 04:08:41'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    20, 5, 'DMMH-0075', 79, 'LOT-3181',
    '2026-02-20', 405409, 'pending', 2, '2025-04-27 16:27:10', 1, '2025-04-28 01:27:10'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    20, 8, 'DMMH-0076', 37, 'LOT-9523',
    '2026-04-25', 493983, 'completed', 1, '2025-05-04 09:54:42', 2, '2025-05-05 23:54:42'
);
INSERT INTO ImportReceiptLineItem (
    ReceiptID, ProductID, LineItemNumber, ReceivedQuantity, LotNumber,
    ExpirationDate, UnitCost, Status, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt
) VALUES (
    20, 10, 'DMMH-0077', 21, 'LOT-4214',
    '2025-12-05', 113820, 'inspected', 2, '2025-05-03 02:24:59', 2, '2025-05-03 16:24:59'
);