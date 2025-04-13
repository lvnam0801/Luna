CREATE TABLE Location (
    LocationID INT AUTO_INCREMENT PRIMARY KEY,       -- Unique identifier for each location
    LocationType VARCHAR(100) NOT NULL,              -- Type of location (e.g., Shelf, Bin, Zone)
    Value VARCHAR(100) NOT NULL,                     -- Identifier or label for the location
    Unit VARCHAR(50) NOT NULL,                       -- Unit of measurement or capacity
    Status ENUM('active', 'inactive') DEFAULT 'active', -- Status of the location
    WarehouseID INT NOT NULL,                        -- Foreign key to the Warehouse table

    FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID) ON DELETE CASCADE,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
