-- -- DROP TABLE IF EXISTS ExportOrderHeader;
-- CREATE TABLE ExportOrderHeader (
--     OrderID INT AUTO_INCREMENT PRIMARY KEY,           -- Unique export order identifier
--     CustomerID INT NOT NULL,                           -- Customer placing the order
--     CarrierID INT,                                     -- Optional carrier
--     WarehouseID INT NOT NULL,                          -- Warehouse fulfilling the order
--     ShippingAddressID INT NOT NULL,                    -- Shipping address
    
--     OrderNumber VARCHAR(100) NOT NULL UNIQUE,          -- External or internal order number
--     OrderDate DATE NOT NULL,                           -- Date order is placed
--     RequestedDeliveryDate DATE,                        -- Requested delivery date
--     ShippingMethod VARCHAR(100),                       -- Shipping method (ground, express, etc.)
--     OrderStatus ENUM('pending', 'processing', 'shipped', 'cancelled') DEFAULT 'pending', -- Order status
--     Notes TEXT,                                        -- Additional notes
--     Status ENUM('active', 'inactive') DEFAULT 'active', -- Record activity status
--     CreatedBy INT,                                     -- User who created the record
--     CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,     -- When record was created
--     UpdatedBy INT,                                     -- User who last updated the record
--     UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- When record was updated,
--     ReceiptID INT,                                     -- (Optional) Link to ImportReceiptHeader for direct transition
--     ExportPurpose ENUM ('sale', 'transfer') DEFAULT 'sale',
--     DestinationWarehouseID INT,
--     LinkedImportReceiptID INT,


--     FOREIGN KEY (ReceiptID) REFERENCES ImportReceiptHeader(ReceiptID) ON DELETE SET NULL,
--     FOREIGN KEY (CustomerID) REFERENCES Party(PartyID) ON DELETE CASCADE,
--     FOREIGN KEY (CarrierID) REFERENCES Party(PartyID) ON DELETE SET NULL,
--     FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID) ON DELETE CASCADE,
--     FOREIGN KEY (ShippingAddressID) REFERENCES Address(AddressID) ON DELETE CASCADE,
--     FOREIGN KEY (CreatedBy) REFERENCES User(UserID) ON DELETE SET NULL,
--     FOREIGN KEY (UpdatedBy) REFERENCES User(UserID) ON DELETE SET NULL,
--     FOREIGN KEY (DestinationWarehouseID) REFERENCES Warehouse(WarehouseID) ON DELETE SET NULL,
--     FOREIGN KEY (LinkedImportReceiptID) REFERENCES ImportReceiptHeader(ReceiptID) ON DELETE SET NULL
-- );

-- ALTER TABLE ExportOrderHeader
--     MODIFY ShippingAddressID INT NULL;