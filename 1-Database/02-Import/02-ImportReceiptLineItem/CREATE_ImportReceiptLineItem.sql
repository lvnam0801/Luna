CREATE TABLE ImportReceiptLineItem (
    ReceiptLineItemID INT AUTO_INCREMENT PRIMARY KEY,       -- Unique ID for this line item
    WarehouseID INT,
    ReceiptID INT NOT NULL,                                 -- The receipt this item belongs to
    ProductID INT NOT NULL,                                    -- The SKU item being received
    LineItemNumber VARCHAR(100) NOT NULL UNIQUE,                                     -- Sequential number for the line in the receipt

    ReceivedQuantity INT NOT NULL DEFAULT 0,                -- Actual quantity received

    LotNumber VARCHAR(100),                                 -- Lot or batch number (if applicable)
    ExpirationDate DATE,                                    -- Expiry date for perishable items
    UnitCost BIGINT NOT NULL,                                 -- Cost per unit for inventory valuation

    Status ENUM('pending', 'inspected', 'partially_putaway', 'completed', 'cancelled') DEFAULT 'pending', -- Line item processing status,
    CreatedBy INT,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UpdatedBy INT,
    UpdatedAt  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (WarehouseID) REFRENCES Warehouse(WarehouseID) ON DELETE CASCADE,
    FOREIGN KEY (ReceiptID) REFERENCES ImportReceiptHeader(ReceiptID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE,
    FOREIGN KEY (CreatedBy) REFERENCES User(UserID) ON DELETE SET NULL,
    FOREIGN KEY (UpdatedBy) REFERENCES User(UserID) ON DELETE SET NULL
);

-- ALTER TABLE ImportReceiptLineItem 
--     ADD WarehouseID INT AFTER ReceiptLineItemID;
-- ALTER TABLE ImportReceiptLineItem 
--     ADD FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID); 

-- ALTER TABLE ImportReceiptLineItem DROP COLUMN WarehouseID;

-- ALTER TABLE ImportReceiptLineItem DROP FOREIGN KEY ImportReceiptLineItem_ibfk_5; 