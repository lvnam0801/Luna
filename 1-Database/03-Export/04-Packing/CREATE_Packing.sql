-- CREATE TABLE Packing (
--     PackingID INT AUTO_INCREMENT PRIMARY KEY,
--     OrderID INT NOT NULL,                  -- Link to ExportOrderLineItem
--     WarehouseID INT,
--     PackToLocationID INT,                 -- Destination location (e.g., shipping dock)
    
--     PackingNumber VARCHAR(100) NOT NULL UNIQUE, -- Unique packing identifier
--     PackedBy INT,
--     PackedDate DATE,
--     Status ENUM('pending', 'completed', 'cancelled') DEFAULT 'pending',
    
--     CreatedBy INT,
--     CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     UpdatedBy INT,
--     UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

--     FOREIGN KEY (OrderID) REFERENCES ExportOrderHeader(OrderID) ON DELETE CASCADE,
--     FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID) ON DELETE SET NULL,
--     FOREIGN KEY (PackToLocationID) REFERENCES Location(LocationID) ON DELETE SET NULL,
--     FOREIGN KEY (PackedBy) REFERENCES User(UserID) ON DELETE SET NULL,
--     FOREIGN KEY (CreatedBy) REFERENCES User(UserID) ON DELETE SET NULL,
--     FOREIGN KEY (UpdatedBy) REFERENCES User(UserID) ON DELETE SET NULL
-- );

-- ALTER TABLE Packing
--     ADD WarehouseID INT AFTER OrderID;

-- ALTER TABLE Packing
--     ADD FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID);