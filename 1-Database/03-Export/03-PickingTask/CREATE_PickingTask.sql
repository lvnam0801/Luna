CREATE TABLE PickingTask (
    PickingTaskID INT AUTO_INCREMENT PRIMARY KEY,          -- Unique task ID
    OrderLineItemID INT NOT NULL,                          -- Reference to the export order line item
    LocationID INT NOT NULL,                               -- Where the item is picked from
    PickedBy INT,                                          -- User who performed the picking
    QuantityPicked INT NOT NULL,                           -- Number of units picked
    PickedDate DATE NOT NULL,                              -- Date when the item was picked
    Status ENUM('pending', 'in_progress', 'completed', 'cancelled') DEFAULT 'pending', -- Task status

    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (OrderLineItemID) REFERENCES ExportOrderLineItem(OrderLineItemID) ON DELETE CASCADE,
    FOREIGN KEY (LocationID) REFERENCES Location(LocationID) ON DELETE CASCADE,
    FOREIGN KEY (PickedBy) REFERENCES User(UserID) ON DELETE SET NULL
);
