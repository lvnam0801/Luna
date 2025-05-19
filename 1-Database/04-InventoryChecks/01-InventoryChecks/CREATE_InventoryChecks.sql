CREATE TABLE InventoryChecks (
    InventoryCheckID INT PRIMARY KEY AUTO_INCREMENT,
    InventoryCheckNumber VARCHAR(100),
    WarehouseID INT NOT NULL,
    CheckedBy INT,
    CheckedDate DATETIME,
    CreatedBy INT,
    CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    UpdatedBy INT,
    UpdatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    -- Foreign Keys
    CONSTRAINT fk_inventory_check_warehouse FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID),
    CONSTRAINT fk_inventory_check_checked_by FOREIGN KEY (CheckedBy) REFERENCES User(UserID),
    CONSTRAINT fk_inventory_check_created_by FOREIGN KEY (CreatedBy) REFERENCES User(UserID),
    CONSTRAINT fk_inventory_check_updated_by FOREIGN KEY (UpdatedBy) REFERENCES User(UserID)
);