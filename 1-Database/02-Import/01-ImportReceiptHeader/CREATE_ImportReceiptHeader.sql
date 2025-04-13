CREATE TABLE ImportReceiptHeader (
    ReceiptID INT AUTO_INCREMENT PRIMARY KEY,                 -- Unique identifier for this import receipt
    ReceiptNumber VARCHAR(100) NOT NULL UNIQUE,               -- External/internal receipt number
    ASNNumber VARCHAR(100),                                   -- Advanced Shipping Notice number
    PONumber VARCHAR(100),                                    -- Purchase Order number
    OriginLocationID INT,                                     -- Foreign key to Address table
    ExpectedArrivalDate DATE,
    ActualArrivalDate DATE,
    ReceivingDock VARCHAR(100),
    ReceiptStatus ENUM('pending', 'in_progress', 'completed', 'cancelled') DEFAULT 'pending',
    Notes TEXT,
    CreatedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CarrierID INT,
    SupplierID INT,
    WarehouseID INT NOT NULL,
    CreatedBy INT,

    FOREIGN KEY (OriginLocationID) REFERENCES Address(AddressID) ON DELETE SET NULL,
    FOREIGN KEY (CarrierID) REFERENCES Party(PartyID) ON DELETE SET NULL,
    FOREIGN KEY (SupplierID) REFERENCES Party(PartyID) ON DELETE SET NULL,
    FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID) ON DELETE CASCADE,
    FOREIGN KEY (CreatedBy) REFERENCES User(UserID) ON DELETE SET NULL
);
