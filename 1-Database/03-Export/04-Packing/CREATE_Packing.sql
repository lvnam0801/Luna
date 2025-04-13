CREATE TABLE Packing (
    PackingID INT AUTO_INCREMENT PRIMARY KEY,            -- Unique identifier for the packing record
    OrderID INT NOT NULL,                                -- The export order being packed
    PackedBy INT,                                        -- User who packed the order
    PackingDate DATE NOT NULL,                           -- Date when packing occurred
    PackageNumber VARCHAR(100) NOT NULL,                 -- Unique package identifier (for tracking/labeling)
    PackageWeight DECIMAL(10, 2),                        -- Total weight of the package
    PackageDimension VARCHAR(100),                       -- Description of the size (e.g., "30x20x15 cm")
    Notes TEXT,                                          -- Additional notes or instructions
    Status ENUM('pending', 'in_progress', 'packed', 'shipped', 'cancelled') DEFAULT 'pending', -- Current packing status


    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (OrderID) REFERENCES ExportOrderHeader(OrderID) ON DELETE CASCADE,
    FOREIGN KEY (PackedBy) REFERENCES User(UserID) ON DELETE SET NULL
);
