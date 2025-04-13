

CREATE TABLE DirectTransition (
    DirectTransitionID INT AUTO_INCREMENT PRIMARY KEY,     -- Unique identifier for the direct transition
    ReceiptID INT NOT NULL,                                -- The import receipt this transition is based on
    OrderID INT NOT NULL,                                  -- The export order this transition leads to
    TransitionDate DATE NOT NULL,                          -- Date of the direct transition

    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (ReceiptID) REFERENCES ImportReceiptHeader(ReceiptID) ON DELETE CASCADE,
    FOREIGN KEY (OrderID) REFERENCES ExportOrderHeader(OrderID) ON DELETE CASCADE
);