
-- CREATE TABLE PickingTask (
--     PickingTaskID INT AUTO_INCREMENT PRIMARY KEY,
--     OrderLineItemID INT NOT NULL,       -- FK to ExportOrderLineItem
--     SKUItemID INT,             -- FK to SKUItem (item being picked)
--     WarehouseID INT,
--     PickFromLocationID INT,    -- FK to Location

--     PickingNumber VARCHAR(100) NOT NULL UNIQUE,
--     PickedQuantity INT NOT NULL,

--     PickedBy INT,                       -- FK to User
--     PickedDate DATE NULL,            -- Date of picking action
--     Status ENUM('pending', 'completed', 'cancelled') DEFAULT 'pending',


--     CreatedBy INT,
--     CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     UpdatedBy INT,
--     UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

--     FOREIGN KEY (OrderLineItemID) REFERENCES ExportOrderLineItem(OrderLineItemID) ON DELETE CASCADE,
--     FOREIGN KEY (SKUItemID) REFERENCES SKUItem(ItemID) ON DELETE NO ACTION,
--     FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID) ON DELETE NO ACTION,
--     FOREIGN KEY (PickFromLocationID) REFERENCES Location(LocationID) ON DELETE SET NULL,
--     FOREIGN KEY (PickedBy) REFERENCES User(UserID) ON DELETE SET NULL,
--     FOREIGN KEY (CreatedBy) REFERENCES User(UserID) ON DELETE SET NULL,
--     FOREIGN KEY (UpdatedBy) REFERENCES User(UserID) ON DELETE SET NULL
-- );

-- ALTER TABLE Packing
-- ADD COLUMN OrderLineItemID INT,
-- ADD CONSTRAINT fk_packing_orderlineitem
--     FOREIGN KEY (OrderLineItemID)
--     REFERENCES ExportOrderLineItem(OrderLineItemID)
--     ON DELETE SET NULL;

-- ALTER TABLE PickingTask
--     ADD WarehouseID INT AFTER SKUItemID;

-- ALTER TABLE PickingTask
--     ADD FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID);