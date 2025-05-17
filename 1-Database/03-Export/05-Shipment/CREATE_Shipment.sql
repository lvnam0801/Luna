-- -- DROP TABLE IF EXISTS Shipment;
-- CREATE TABLE Shipment (
--     ShipmentID INT AUTO_INCREMENT PRIMARY KEY,                      -- Unique identifier
--     OrderID INT NOT NULL,                                           -- Belongs to an export order
--     CarrierID INT,                                                  -- Party carrying the shipment (nullable)
--     WarehouseID INT,
--     ShipFromLocationID INT,                                -- Origin location (from warehouse)
--     ShipToAddressID INT,                                   -- Destination address (customer/supplier)
    
--     ShipmentNumber VARCHAR(100) NOT NULL UNIQUE,                    -- Human-readable shipment number
--     ShippedDate DATE,                                               -- When it was shipped
--     ExpectedArrivalDate DATE,                                       -- Planned arrival
--     ActualArrivalDate DATE,                                         -- Actual arrival

--     ShipmentStatus ENUM('pending', 'in_transit', 'delivered', 'cancelled') DEFAULT 'pending',
--     Notes TEXT,                                                     -- Any notes from logistics
--     Status ENUM('active', 'inactive') DEFAULT 'active',             -- Soft-delete or archival flag

--     CreatedBy INT,
--     CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     UpdatedBy INT,
--     UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

--     FOREIGN KEY (OrderID) REFERENCES ExportOrderHeader(OrderID) ON DELETE CASCADE,
--     FOREIGN KEY (CarrierID) REFERENCES Party(PartyID) ON DELETE SET NULL,
--     FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID) ON DELETE SET NULL,
--     FOREIGN KEY (ShipFromLocationID) REFERENCES Location(LocationID) ON DELETE SET NULL,
--     FOREIGN KEY (ShipToAddressID) REFERENCES Address(AddressID) ON DELETE SET NULL,
--     FOREIGN KEY (CreatedBy) REFERENCES User(UserID) ON DELETE SET NULL,
--     FOREIGN KEY (UpdatedBy) REFERENCES User(UserID) ON DELETE SET NULL
-- );


-- ALTER TABLE Shipment
--     ADD WarehouseID INT AFTER CarrierID;

-- ALTER TABLE Shipment
--     ADD FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID);
