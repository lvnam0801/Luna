CREATE TABLE ImportReceiptHeader (
    ReceiptID INT AUTO_INCREMENT PRIMARY KEY,                 -- Unique identifier for this import receipt
    SupplierID INT,
    CarrierID INT,
    WarehouseID INT NOT NULL,
    ReceivingDockID INT NULL,
    OriginLocationID INT,                                     -- Foreign key to Address table

    ReceiptNumber VARCHAR(100) NOT NULL UNIQUE,               -- External/internal receipt number
    ASNNumber VARCHAR(100),                                   -- Advanced Shipping Notice number
    PONumber VARCHAR(100),                                    -- Purchase Order number
    ExpectedArrivalDate DATE,
    ActualArrivalDate DATE,
    ReceiptStatus ENUM('pending', 'in_progress', 'completed', 'cancelled') DEFAULT 'pending',
    Notes TEXT,

    CreatedBy INT,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedBy INT,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    Status ENUM('active', 'inactive') DEFAULT 'active',

    FOREIGN KEY (OriginLocationID) REFERENCES Address(AddressID) ON DELETE SET NULL,
    FOREIGN KEY (CarrierID) REFERENCES Party(PartyID) ON DELETE SET NULL,
    FOREIGN KEY (SupplierID) REFERENCES Party(PartyID) ON DELETE SET NULL,
    FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID) ON DELETE CASCADE,
    FOREIGN KEY (ReceivingDockID) REFERENCES Location(LocationID) ON DELETE SET NULL,
    FOREIGN KEY (CreatedBy) REFERENCES User(UserID) ON DELETE SET NULL,
    FOREIGN KEY (UpdatedBy) REFERENCES User(UserID) ON DELETE SET NULL
);