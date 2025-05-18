CREATE TABLE SKUItem (
    ItemID INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for each SKU item
    SKU VARCHAR(100) NOT NULL UNIQUE,       -- Unique SKU code
    Quantity INT NOT NULL DEFAULT 0,        -- Quantity available
    ProductID INT NOT NULL,                 -- Reference to Product table
    ReceiptLineItemID INT,
    WarehouseID INT,
    LocationID INT,
    Status ENUM('in_stock', 'quarantined', 'archived', 'inactive') DEFAULT 'in_stock',

    FOREIGN KEY (ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE,
    FOREIGN KEY (ReceiptLineItemID) REFERENCES ImportReceiptLineItem(ReceiptLineItemID) ON DELETE SET NULL,
    FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID) ON DELETE SET NULL,
    FOREIGN KEY (LocationID) REFERENCES Location(LocationID) ON DELETE SET NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ALTER TABLE SKUItem
--     ADD ReceiptLineItemID INT AFTER ProductID;

-- ALTER TABLE SKUItem 
--     ADD WarehouseID INT AFTER ReceiptLineItemID,
--     ADD FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID),
--     ADD LocationID INT AFTER WarehouseID,
--     ADD FOREIGN KEY (LocationID) REFERENCES Location(LocationID);