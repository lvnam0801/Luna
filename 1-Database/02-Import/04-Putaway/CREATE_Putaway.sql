-- DROP TABLE Putaway;
CREATE TABLE Putaway (
    PutawayID INT AUTO_INCREMENT PRIMARY KEY,                  -- Unique identifier
    PutawayNumber VARCHAR(100) NOT NULL UNIQUE,  -- Unique identifier for the putaway action
    ReceiptLineItemID INT NOT NULL,                            -- Reference to the receipt line item
    PutawayAtLocationID INT NOT NULL,                            -- Storage location where items are placed
    SKUItemID INT NULL,                                        -- SKU item being put away
    
    Quantity INT NOT NULL,                                     -- Number of items put away
    PutawayResult ENUM('stored', 'quarantined') NULL,      -- Final outcome of the putaway
    
    Status ENUM('pending', 'completed', 'cancelled') DEFAULT 'pending',  -- Process status of this putaway action
    PutawayBy INT,                                              -- User who performed the action
    PutawayDate DATE NOT NULL,                                  -- Execution date
    
    CreatedBy INT,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedBy INT,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (ReceiptLineItemID) REFERENCES ImportReceiptLineItem(ReceiptLineItemID) ON DELETE CASCADE,
    FOREIGN KEY (PutawayAtLocationID) REFERENCES Location(LocationID) ON DELETE CASCADE,
    FOREIGN KEY (SKUItemID) REFERENCES SKUItem(ItemID) ON DELETE CASCADE,
    FOREIGN KEY (PutawayBy) REFERENCES User(UserID) ON DELETE SET NULL,
    FOREIGN KEY (CreatedBy) REFERENCES User(UserID) ON DELETE SET NULL,
    FOREIGN KEY (UpdatedBy) REFERENCES User(UserID) ON DELETE SET NULL
);