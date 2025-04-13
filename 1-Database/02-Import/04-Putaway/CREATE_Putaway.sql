CREATE TABLE Putaway (
    PutawayID INT AUTO_INCREMENT PRIMARY KEY,          -- Unique identifier for the putaway record
    ReceiptLineItemID INT NOT NULL,                    -- Link to the received line item
    LocationID INT NOT NULL,                           -- Storage location where the item was placed
    PutawayBy INT,                                      -- User who performed the putaway
    Quantity INT NOT NULL,                             -- Quantity of items put away
    PutawayDate DATE NOT NULL,                         -- Date of the putaway action
    Status ENUM('pending', 'completed', 'cancelled') DEFAULT 'pending', -- Putaway status

    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (ReceiptLineItemID) REFERENCES ImportReceiptLineItem(ReceiptLineItemID) ON DELETE CASCADE,
    FOREIGN KEY (LocationID) REFERENCES Location(LocationID) ON DELETE CASCADE,
    FOREIGN KEY (PutawayBy) REFERENCES User(UserID) ON DELETE SET NULL
);
