CREATE TABLE Packing (
    PackingID INT AUTO_INCREMENT PRIMARY KEY,
    PackingNumber VARCHAR(100) NOT NULL UNIQUE, -- Unique packing identifier
    OrderID INT NOT NULL,                  -- Link to ExportOrderLineItem
    PackToLocationID INT,                 -- Destination location (e.g., shipping dock)
    Status ENUM('pending', 'packed', 'cancelled') DEFAULT 'pending',
    PackedBy INT,
    PackedDate DATE,
    CreatedBy INT,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedBy INT,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (OrderID) REFERENCES ExportOrderHeader(OrderID) ON DELETE CASCADE,
    FOREIGN KEY (PackToLocationID) REFERENCES Location(LocationID) ON DELETE SET NULL,
    FOREIGN KEY (PackedBy) REFERENCES User(UserID) ON DELETE SET NULL,
    FOREIGN KEY (CreatedBy) REFERENCES User(UserID) ON DELETE SET NULL,
    FOREIGN KEY (UpdatedBy) REFERENCES User(UserID) ON DELETE SET NULL
);