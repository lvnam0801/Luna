
CREATE TABLE PickingTask (
    PickingTaskID INT AUTO_INCREMENT PRIMARY KEY,
    PickingNumber VARCHAR(100) NOT NULL UNIQUE,
    OrderLineItemID INT NOT NULL,       -- FK to ExportOrderLineItem
    SKUItemID INT,             -- FK to SKUItem (item being picked)
    PickFromLocationID INT,    -- FK to Location

    PickedQuantity INT NOT NULL,

    Status ENUM('pending', 'completed', 'cancelled') DEFAULT 'pending',
    PickedBy INT,                       -- FK to User
    PickedDate DATE NOT NULL,            -- Date of picking action


    CreatedBy INT,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedBy INT,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (OrderLineItemID) REFERENCES ExportOrderLineItem(OrderLineItemID) ON DELETE CASCADE,
    FOREIGN KEY (SKUItemID) REFERENCES SKUItem(ItemID) ON DELETE NO ACTION,
    FOREIGN KEY (PickFromLocationID) REFERENCES Location(LocationID) ON DELETE SET NULL,
    FOREIGN KEY (PickedBy) REFERENCES User(UserID) ON DELETE SET NULL,
    FOREIGN KEY (CreatedBy) REFERENCES User(UserID) ON DELETE SET NULL,
    FOREIGN KEY (UpdatedBy) REFERENCES User(UserID) ON DELETE SET NULL
);
