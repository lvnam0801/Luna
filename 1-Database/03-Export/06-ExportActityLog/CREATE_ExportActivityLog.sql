CREATE TABLE ExportActivityLog (
    ActivityLogID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT NOT NULL,                        -- Links to ExportOrderHeader
    UserID INT,                                  -- User who triggered the action

    TargetType ENUM(
        'Order', 'LineItem', 'PickingTask', 'Packing', 'Shipment', 'SKUItem'
    ) NOT NULL,                                  -- What kind of component is affected

    ActionType ENUM(
        'create', 'update', 'delete'
    ) NOT NULL,                                  -- What kind of action was performed

    TargetID INT,                                -- The specific ID of affected row
    LoggedTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- When it happened
    Content TEXT NOT NULL,                       -- Description of the activity
    Status ENUM('active', 'inactive') DEFAULT 'active',

    FOREIGN KEY (OrderID) REFERENCES ExportOrderHeader(OrderID) ON DELETE CASCADE,
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE SET NULL
);