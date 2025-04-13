CREATE TABLE ImportReceiptLineItem (
    ReceiptLineItemID INT AUTO_INCREMENT PRIMARY KEY,       -- Unique ID for this line item
    ReceiptID INT NOT NULL,                                 -- The receipt this item belongs to
    ItemID INT NOT NULL,                                    -- The SKU item being received
    LineItemNumber INT,                                     -- Sequential number for the line in the receipt

    ExpectedQuantity INT NOT NULL,                          -- Quantity expected from supplier
    ReceivedQuantity INT NOT NULL DEFAULT 0,                -- Actual quantity received
    QuantityDiscrepancy INT GENERATED ALWAYS AS (ReceivedQuantity - ExpectedQuantity) STORED, -- Auto-calculated discrepancy
    DiscrepancyReasonCode VARCHAR(100),                     -- Code describing why there is a quantity mismatch

    LotNumber VARCHAR(100),                                 -- Lot or batch number (if applicable)
    SerialNumber VARCHAR(100),                              -- Serial number for the item (if tracked individually)
    ExpirationDate DATE,                                    -- Expiry date for perishable items
    UnitCost DECIMAL(10,2),                                 -- Cost per unit for inventory valuation

    Status ENUM('pending', 'inspected', 'stored', 'cancelled') DEFAULT 'pending', -- Line item processing status

    FOREIGN KEY (ReceiptID) REFERENCES ImportReceiptHeader(ReceiptID) ON DELETE CASCADE,
    FOREIGN KEY (ItemID) REFERENCES SKUItem(ItemID) ON DELETE CASCADE,

    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,          -- When this line was created
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Last update timestamp
);
