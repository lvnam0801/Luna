CREATE TABLE InventoryCheckDetails (
    InventoryCheckDetailsID INT PRIMARY KEY AUTO_INCREMENT,
    InventoryCheckID INT NOT NULL,
    SKUItemID INT NOT NULL,
    SystemQuantity INT NOT NULL,
    ActualQuantity INT NOT NULL,
    QuantityDifferenceReason VARCHAR(255),
    Note TEXT,
    Status ENUM('active', 'inactive') DEFAULT 'active',

    -- Foreign Keys
    CONSTRAINT fk_check_detail_inventory_check FOREIGN KEY (InventoryCheckID) REFERENCES InventoryChecks(InventoryCheckID),
    CONSTRAINT fk_check_detail_skuitem FOREIGN KEY (SKUItemID) REFERENCES SKUItem(ItemID)
);